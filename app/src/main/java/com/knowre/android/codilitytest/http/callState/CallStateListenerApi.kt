package com.knowre.android.codilitytest.http.callState


internal interface CallStateListenerApi<in Call> {
    fun onInitialCallStarted(call: Call, id: String?)
    fun onRetryingBackoffStarted(call: Call, id: String?, throwable: Throwable, backOffDelay: Long, retryCount: Int)
    fun onRetryCallStarted(call: Call, id: String?, throwable: Throwable, retryCount: Int)
    fun onSucceed(call: Call, id: String?)
    fun onFailure(call: Call, id: String?, throwable: Throwable, retryCount: Int)
    fun onResponseIgnored(call: Call, id: String?)
}