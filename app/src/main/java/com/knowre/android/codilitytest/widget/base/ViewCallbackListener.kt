package com.knowre.android.codilitytest.widget.base


internal interface ViewCallbackListener<A : ViewAction> {
    fun onAction(action: A)
}