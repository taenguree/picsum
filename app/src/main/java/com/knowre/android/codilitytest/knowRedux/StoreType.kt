package com.knowre.android.codilitytest.knowRedux


internal interface StoreType<out S: StateType, in A: Action> {
    fun get(): S
    fun dispatch(action: A)
    fun subscribe(observer: StateObserver<S>)
    fun dispose(observer: StateObserver<S>)
    fun dispose()
}
