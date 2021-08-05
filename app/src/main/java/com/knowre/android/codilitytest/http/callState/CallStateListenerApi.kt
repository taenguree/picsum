package com.knowre.android.codilitytest.http.callState


internal interface CallStateListenerApi<in Call> {
    fun onInitialCallStarted(call: Call)
    fun onRetryingBackoffStarted(call: Call, throwable: Throwable, backOffDelay: Long, retryCount: Int)
    fun onRetryCallStarted(call: Call, throwable: Throwable, retryCount: Int)
    fun onSucceed(call: Call)
    fun onFailure(call: Call, throwable: Throwable, retryCount: Int)
    fun onResponseIgnored(call: Call)
}