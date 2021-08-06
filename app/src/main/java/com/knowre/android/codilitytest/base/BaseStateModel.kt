package com.knowre.android.codilitytest.base

import android.util.Log
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemote
import com.knowre.android.codilitytest.knowRedux.*
import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.MiddlewareType
import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.knowRedux.ViewStateType
import com.knowre.android.codilitytest.knowRedux.co.CoroutineAwareKnowReduxStore
import com.knowre.android.codilitytest.knowRedux.co.CoroutineStateObserver
import com.knowre.android.codilitytest.navigator.NavigatorApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


internal abstract class BaseStateModel<VS : ViewStateType, S : ViewStateAware<VS>, A : Action> constructor(
        initialState: S,
        reducer: Reducer<S, A>,
        middleware: MiddlewareType<S, A>? = null

) : CoroutineStateObserver<S, A> {

    interface Listener<in A> {
        suspend fun onPreAction(action: A)
        suspend fun onPostAction(action: A)
    }

    protected val store = CoroutineAwareKnowReduxStore(
            initialState = initialState,
            reducer = reducer,
            middleware = middleware
    )
            .apply { subscribe(this@BaseStateModel) }

    protected var navigator: NavigatorApi? = null; private set

    private var renderer: StateRenderer<VS, A>? = null

    private var listener: Listener<A>? = null

    private var scope: CoroutineScope? = null

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is CoroutineRemote.RetryExhaustedException -> Log.d("RetryExhaustedException", "Retry Exhausted")

            else -> {
                throwable.printStackTrace()
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), throwable)
            }
        }
    }

    override suspend fun onNext(state: S, action: A) {
        @Suppress("UNCHECKED_CAST")
        renderer?.render(state.viewState, action)
    }

    suspend fun dispatch(action: A) {
        listener?.onPreAction(action)
        store.dispatch(action)
        listener?.onPostAction(action)
    }

    fun launch(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit): Job? {
        return scope!!.launch(context = context + exceptionHandler, block = block)
    }

    fun getState() = store.get()

    fun setNavigator(navigator: NavigatorApi) {
        this.navigator = navigator
    }

    fun setRenderer(layout: StateRenderer<VS, A>) {
        this.renderer = layout

        if (renderer is ActionEmitter<*>) {
            @Suppress("UNCHECKED_CAST")
            (renderer as ActionEmitter<A>).setListener(object : ActionListener<A> {
                override fun onAction(action: A) {
                    launch {
                        dispatch(action)
                    }
                }
            })
        }
    }

    fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    fun setListener(listener: Listener<A>) {
        this.listener = listener
    }

    protected fun getScope() = this.scope

}