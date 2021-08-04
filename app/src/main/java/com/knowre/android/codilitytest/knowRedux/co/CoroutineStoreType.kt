package com.knowre.android.codilitytest.knowRedux.co

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.StateType


internal interface CoroutineStoreType<out S: StateType, A: Action> {
    fun get(): S
    suspend fun dispatch(action: A)
    fun subscribe(observer: CoroutineStateObserver<S, A>)
    fun dispose(observer: CoroutineStateObserver<S, A>)
    fun dispose()
}
