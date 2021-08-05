package com.knowre.android.codilitytest.http.retry


internal interface RetryPolicyApi {
    fun shouldRetry(encodedPath: String, throwable: Throwable): Boolean
}