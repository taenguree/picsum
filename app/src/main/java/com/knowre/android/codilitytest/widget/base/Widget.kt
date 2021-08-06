package com.knowre.android.codilitytest.widget.base

import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal interface Widget<S : ViewStateType, RenderAction : ViewAction, CallbackAction: ViewAction> : ViewCallbackActionEmitter<CallbackAction> {
    fun render(state: S, action: RenderAction)
}