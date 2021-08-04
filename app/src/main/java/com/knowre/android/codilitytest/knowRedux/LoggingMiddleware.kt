package com.knowre.android.codilitytest.knowRedux

import com.knowre.android.codilitytest.knowRedux.logger.BasicGsonStateLogger
import com.knowre.android.codilitytest.knowRedux.logger.StateLoggerApi


internal open class LoggingMiddleware <T : StateType, A : Action> constructor(
        private val prefix: String = "",
        private val stateLogger: StateLoggerApi<T> = BasicGsonStateLogger(prefix)

) : LinkedMiddleware<T, A>() {

    override fun execute(oldState: T, newState: T, action: A) {
        stateLogger.log(oldState, newState, action)

        proceed(oldState, newState, action)
    }

}