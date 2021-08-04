package com.knowre.android.codilitytest.knowRedux


internal interface Reducer<S : StateType, in A : Action> {
    fun reduce(state: S, action: A): S
}