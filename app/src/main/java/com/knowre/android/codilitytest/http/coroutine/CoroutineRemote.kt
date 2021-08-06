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
    private val callStateListeners: Collection<@JvmSuppressWildcards CallStateListenerApi<@JvmSuppressWildcards Call<*>>>

) : CoroutineRemoteApi {

    class RetryExhaustedException : Exception()

    private val inProgressCalls: CopyOnWriteArrayList<Pair<String?, Call<*>>> = CopyOnWriteArrayList()

    override suspend fun <T> execute(call: Call<T>, id: String?, maxRetryCountOnFail: Int): T {
        return withContext(Dispatchers.IO) { withRetry(call, id = id, currentRetryCount = 0, maxRetryCountOnFail = maxRetryCountOnFail) { coroutineRetrofit.async(call, parentJob = null).await() }.also { inProgressCalls.remove(Pair(id, call)) } }
    }

    override fun cancelAll() {
        inProgressCalls.forEach { callStateListeners.forEach { listener -> listener.onResponseIgnored(it.second, it.first) }}

        inProgressCalls.clear()
    }

    private suspend fun <T> withRetry(call: Call<T>, id: String?, currentRetryCount: Int, maxRetryCountOnFail: Int, throwable: Throwable? = null, callExecutionBlock: suspend () -> T): T {
        return try {
            if (currentRetryCount == 0 && throwable == null) {
                inProgressCalls.add(Pair(id, call))

                callStateListeners.forEach { listener -> listener.onInitialCallStarted(call, id) }
            } else {
                val backOffDelay = 2000L

                if (retryPolicy.shouldRetry(call.request().url().encodedPath(), throwable!!) && currentRetryCount <= maxRetryCountOnFail) {
                    callStateListeners.forEach { listener -> listener.onRetryingBackoffStarted(call, id, throwable, backOffDelay, currentRetryCount) }

                    delay(backOffDelay)

                    callStateListeners.forEach { listener -> listener.onRetryCallStarted(call, id, throwable, currentRetryCount) }
                } else {
                    callStateListeners.forEach { listener -> listener.onFailure(call, id, throwable, currentRetryCount) }

                    throw RetryExhaustedException()
                }
            }

            val result = callExecutionBlock()

            callStateListeners.forEach { listener -> listener.onSucceed(call, id) }

            result
        } catch (t: Throwable) {
            if (t !is RetryExhaustedException) {
                if (currentRetryCount <= maxRetryCountOnFail) {
                    withRetry(call = call, id = id, currentRetryCount = currentRetryCount + 1, maxRetryCountOnFail = maxRetryCountOnFail, throwable = t, callExecutionBlock = callExecutionBlock)
                } else {
                    inProgressCalls.remove(Pair(id, call))

                    throw t
                }
            } else {
                inProgressCalls.remove(Pair(id, call))

                throw t
            }
        }
    }

}