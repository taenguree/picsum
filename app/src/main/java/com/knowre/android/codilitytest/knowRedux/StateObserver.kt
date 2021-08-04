package com.knowre.android.codilitytest.knowRedux


internal interface StateObserver<in S: StateType> {
    fun onNext(state: S, action: Action)
}