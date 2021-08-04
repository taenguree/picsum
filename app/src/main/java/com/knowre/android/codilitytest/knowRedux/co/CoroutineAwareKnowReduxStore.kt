package com.knowre.android.codilitytest.knowRedux.co

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.MiddlewareType
import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.knowRedux.StateType
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicReference


internal class CoroutineAwareKnowReduxStore<S : StateType, A : Action> constructor(
        initialState: S,
        private val reducer: Reducer<S, A>,
        private val middleware: MiddlewareType<S, A>? = null

) : AtomicReference<S>(initialState), CoroutineStoreType<S, A> {

    private val observers: MutableList<CoroutineStateObserver<S, A>> = CopyOnWriteArrayList()

    override suspend fun dispatch(action: A) {
        var oldState: S

        do {
            oldState = get()

            val isSuccessful = compareAndSet(oldState, reducer.reduce(oldState, action))
        } while (!isSuccessful)

        middleware?.execute(oldState, get(), action)

        observers.forEach { it.onNext(get(), action) }
    }

    override fun subscribe(observer: CoroutineStateObserver<S, A>) {
        val index = observers.indexOfFirst { it == observer }

        if (index != -1) {
            observers.removeAt(index)
        }

        observers.add(observer)
    }

    override fun dispose(observer: CoroutineStateObserver<S, A>) {
        val index = observers.indexOfFirst { it == observer }

        if (index != -1) {
            observers.removeAt(index)
        }
    }

    override fun dispose() {
        observers.clear()
    }

}