package com.knowre.android.codilitytest.widget.singlePicture.action

import com.knowre.android.codilitytest.widget.base.ViewAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal sealed class SinglePictureCallbackAction : ViewAction {
    data class Clicked(val state: SinglePictureViewState) : SinglePictureCallbackAction()
}