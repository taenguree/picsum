package com.knowre.android.codilitytest.http.coroutine

import com.knowre.android.codilitytest.http.callState.CallStateListenerApi
import com.knowre.android.codilitytest.http.retry.RetryPolicyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject


internal class CoroutineRemote @Inject constructor(
    private val coroutineRetrofit: CoroutineRetrofitApi,
    private val retryPolicy: RetryPolicyApi,
    private val callStateListener: CallStateListenerApi<@JvmSuppressWildcards Call<*>>

) : CoroutineRemoteApi {

    class RetryExhaustedException : Exception()

    private val inProgressCalls: CopyOnWriteArrayList<Call<*>> = CopyOnWriteArrayList()

    override suspend fun <T> execute(call: Call<T>, maxRetryCountOnFail: Int): T {
        return withContext(Dispatchers.IO) { withRetry(call, currentRetryCount = 0, maxRetryCountOnFail = maxRetryCountOnFail) { coroutineRetrofit.async(call, parentJob = null).await() }.also { inProgressCalls.remove(call) } }
    }

    override fun cancelAll() {
        inProgressCalls.forEach { callStateListener.onResponseIgnored(it) }

        inProgressCalls.clear()
    }

    private suspend fun <T> withRetry(call: Call<T>, currentRetryCount: Int, maxRetryCountOnFail: Int, throwable: Throwable? = null, callExecutionBlock: suspend () -> T): T {
        return try {
            if (currentRetryCount == 0 && throwable == null) {
                inProgressCalls.add(call)

                callStateListener.onInitialCallStarted(call)
            } else {
                val backOffDelay = 2000L

                if (retryPolicy.shouldRetry(call.request().url().encodedPath(), throwable!!) && currentRetryCount <= maxRetryCountOnFail) {
                    callStateListener.onRetryingBackoffStarted(call, throwable, backOffDelay, currentRetryCount)

                    delay(backOffDelay)

                    callStateListener.onRetryCallStarted(call, throwable, currentRetryCount)
                } else {
                    callStateListener.onFailure(call, throwable, currentRetryCount)

                    throw RetryExhaustedException()
                }
            }

            val result = callExecutionBlock()

            callStateListener.onSucceed(call)

            result
        } catch (t: Throwable) {
            if (t !is RetryExhaustedException) {
                withRetry(call = call, currentRetryCount = currentRetryCount + 1, maxRetryCountOnFail = maxRetryCountOnFail, throwable = t, callExecutionBlock = callExecutionBlock)
            } else {
                inProgressCalls.remove(call)

                throw t
            }
        }
    }

}