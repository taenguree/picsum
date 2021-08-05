package com.knowre.android.codilitytest.http.retry

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


internal class RetryPolicy @Inject constructor() : RetryPolicyApi {

    private val retryCodeList = listOf(408, 500, 502, 503)

    override fun shouldRetry(encodedPath: String, throwable: Throwable): Boolean {
        return when (throwable) {
            is IOException   -> true
            is HttpException -> retryCodeList.contains(throwable.code())
            else             -> false
        }
    }

}