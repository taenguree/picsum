package com.knowre.android.codilitytest.screen.detail

import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailState


internal class DetailReducer : Reducer<DetailState, DetailAction> {

    override fun reduce(state: DetailState, action: DetailAction): DetailState {
        return state
    }

}