package com.knowre.android.codilitytest.widget.singlePicture.action

import com.knowre.android.codilitytest.widget.base.ViewAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal sealed class SinglePictureRenderAction : ViewAction {
    data class Render(val state: SinglePictureViewState) : SinglePictureRenderAction()
}