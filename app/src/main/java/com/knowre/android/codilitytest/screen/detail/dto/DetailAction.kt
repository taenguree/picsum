package com.knowre.android.codilitytest.screen.detail.dto

import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.ViewCallbackAction
import com.knowre.android.codilitytest.knowRedux.ViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction


internal sealed class DetailAction : Action {
    sealed class Input : DetailAction() {
        class IntentData(val detailIntentData: DetailIntentData) : Input()

        class OnImageEntityFetched(val imageEntity: ImageEntity) : Input()
    }

    data class Render(override val action: DetailViewRenderAction) : DetailAction(), ViewRenderAction<DetailViewRenderAction>

    data class Callback(override val action: DetailViewCallbackAction) : DetailAction(), ViewCallbackAction<DetailViewCallbackAction>
}