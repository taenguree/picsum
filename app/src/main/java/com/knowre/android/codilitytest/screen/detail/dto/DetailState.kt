package com.knowre.android.codilitytest.screen.detail.dto

import com.knowre.android.codilitytest.knowRedux.StateType
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState


internal class DetailState(
    override val viewState: DetailViewState = DetailViewState(),
    val intentData: DetailIntentData = DetailIntentData()

) : StateType, ViewStateAware<DetailViewState>