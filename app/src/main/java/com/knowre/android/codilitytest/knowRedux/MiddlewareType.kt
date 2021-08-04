package com.knowre.android.codilitytest.knowRedux


internal interface MiddlewareType<in S: StateType, in A: Action> {
    fun execute(oldState: S, newState: S, action: A)
}