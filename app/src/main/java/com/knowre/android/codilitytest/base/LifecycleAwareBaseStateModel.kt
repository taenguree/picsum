package com.knowre.android.codilitytest.base

import android.os.Bundle
import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.MiddlewareType
import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal abstract class LifecycleAwareBaseStateModel<VS: ViewStateType, S : ViewStateAware<VS>, A : Action> constructor(
        initialState: S,
        reducer: Reducer<S, A>,
        middleware: MiddlewareType<S, A>? = null

) : BaseStateModel<VS, S, A>(initialState, reducer, middleware) {

    open fun onCreate(savedInstanceState: Bundle?) = Unit

    open fun onStart() = Unit

    open fun onResume() = Unit

    open fun onPause() = Unit

    open fun onStop() = Unit

    open fun onDestroy() = Unit

    open fun onCleared() = Unit

}