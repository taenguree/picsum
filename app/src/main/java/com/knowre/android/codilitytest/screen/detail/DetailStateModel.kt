package com.knowre.android.codilitytest.screen.detail

import android.content.Intent
import android.os.Bundle
import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import javax.inject.Inject


internal class DetailStateModel @Inject constructor() : LifecycleAwareStateModel<DetailViewState, DetailState, DetailAction>(
    initialState = DetailState(),
    reducer      = DetailReducer(),
    middleware   = LoggingMiddleware("DET")
) {

    override fun onCreate(savedInstanceState: Bundle?, intent: Intent?) {
        super.onCreate(savedInstanceState, intent)

        launch {
            dispatch(DetailAction.Input.IntentData(intent!!.getSerializableExtra(DetailActivity.INTENT_EXTRA_NAME) as DetailIntentData))
        }
    }

}