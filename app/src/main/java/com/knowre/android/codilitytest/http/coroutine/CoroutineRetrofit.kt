package com.knowre.android.codilitytest.http.coroutine

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


internal class CoroutineRetrofit @Inject constructor() : CoroutineRetrofitApi {

    override fun <T> async(call: Call<T>, parentJob: Job?): Deferred<T> {
        val deferred = CompletableDeferred<T>(parentJob)

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.clone().enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    deferred.complete(response.body()!!)
                } else {
                    deferred.completeExceptionally(HttpException(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                deferred.completeExceptionally(t)
            }
        })

        return deferred
    }

}