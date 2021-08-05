package com.knowre.android.codilitytest.widget.pictureList.view.action

import com.knowre.android.codilitytest.widget.base.ViewAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal sealed class PictureListRenderAction : ViewAction {
    data class AppendPictures(val singlePictureStates: List<SinglePictureViewState>) : PictureListRenderAction()
}