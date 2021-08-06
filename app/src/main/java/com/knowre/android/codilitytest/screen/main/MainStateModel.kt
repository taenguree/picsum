package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.main.dto.MainAction
import com.knowre.android.codilitytest.screen.main.dto.MainState
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewState
import javax.inject.Inject


internal class MainStateModel @Inject constructor() : LifecycleAwareStateModel<MainViewState, MainState, MainAction>(
    initialState = MainState(),
    reducer      = MainReducer(),
    middleware   = LoggingMiddleware()
) {}