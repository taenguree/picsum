package com.knowre.android.codilitytest.knowRedux.logger

import com.knowre.android.codilitytest.knowRedux.StateType


internal interface StateLoggerApi <in T : StateType> {
    fun log(oldState: T, newState: T, action: Any)
}