package com.knowre.android.codilitytest.widget.base


internal interface ViewCallbackActionEmitter<CallbackAction: ViewAction> {
    fun setListener(listener: ViewCallbackListener<CallbackAction>)
}