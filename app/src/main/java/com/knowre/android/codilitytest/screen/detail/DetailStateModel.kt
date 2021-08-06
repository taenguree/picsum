package com.knowre.android.codilitytest.screen.detail

import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import javax.inject.Inject


internal class DetailStateModel @Inject constructor() : LifecycleAwareStateModel<DetailViewState, DetailState, DetailAction>(
    initialState = DetailState(),
    reducer      = DetailReducer(),
    middleware   = LoggingMiddleware()
) {
}