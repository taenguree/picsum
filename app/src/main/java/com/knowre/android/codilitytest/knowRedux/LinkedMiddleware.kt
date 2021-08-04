package com.knowre.android.codilitytest.knowRedux


internal abstract class LinkedMiddleware<S: StateType, A: Action> : MiddlewareType<S, A> {

    private var next: LinkedMiddleware<S, A>? = null

    fun link(middleware: LinkedMiddleware<S, A>): LinkedMiddleware<S, A> {
        next = middleware

        return this
    }

    protected fun proceed(oldState: S, newState: S, action: A) {
        next?.execute(oldState, newState, action)
    }

}