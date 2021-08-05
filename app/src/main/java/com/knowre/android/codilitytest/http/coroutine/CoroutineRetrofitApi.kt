package com.knowre.android.codilitytest.http.coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import retrofit2.Call


internal interface CoroutineRetrofitApi {
    fun <T> async(call: Call<T>, parentJob: Job? = null): Deferred<T>
}