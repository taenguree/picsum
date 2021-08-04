package com.knowre.android.codilitytest.knowRedux.co

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.StateType


internal interface CoroutineStateObserver<in S : StateType, in A : Action> {
    suspend fun onNext(state: S, action: A)
}