package com.knowre.android.codilitytest.knowRedux


internal interface StateType

internal interface ViewStateType

internal interface ViewStateAware<VS : ViewStateType> : StateType {
    val viewState: VS
}