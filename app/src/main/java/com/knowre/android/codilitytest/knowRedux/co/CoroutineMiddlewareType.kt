package com.knowre.android.codilitytest.knowRedux.co

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.StateType


internal interface CoroutineMiddlewareType<in S: StateType, in A: Action> {
    suspend fun execute(oldState: S, newState: S, action: A)
}