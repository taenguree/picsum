package com.knowre.android.codilitytest.screen.detail

import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction


internal class DetailReducer : Reducer<DetailState, DetailAction> {

    override fun reduce(state: DetailState, action: DetailAction): DetailState {
        return when (action) {
            is DetailAction.Input.IntentData -> state.copy(intentData = action.detailIntentData)

            is DetailAction.Render -> when (val renderAction = action.action) {
                is DetailViewRenderAction.Render -> state.copy(viewState = renderAction.state)
            }

            else -> state
        }
    }

}