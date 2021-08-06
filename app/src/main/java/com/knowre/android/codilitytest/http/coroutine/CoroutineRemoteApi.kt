package com.knowre.android.codilitytest.http.coroutine

import retrofit2.Call


internal interface CoroutineRemoteApi {
    suspend fun <T> execute(call: Call<T>, id: String? = null, maxRetryCountOnFail: Int = 2): T
    fun cancelAll()
}