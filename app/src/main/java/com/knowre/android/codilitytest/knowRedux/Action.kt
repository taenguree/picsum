package com.knowre.android.codilitytest.knowRedux


internal interface Action

internal interface ViewRenderAction<T : Any> : Action { val action: T }

internal interface ViewCallbackAction<T : Any> : Action { val action: T }