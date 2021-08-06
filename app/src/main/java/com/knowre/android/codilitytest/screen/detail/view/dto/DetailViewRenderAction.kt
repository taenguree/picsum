package com.knowre.android.codilitytest.screen.detail.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction


internal sealed class DetailViewRenderAction : ViewAction {
    data class Render(val state: DetailViewState) : DetailViewRenderAction()
}