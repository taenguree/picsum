package com.knowre.android.codilitytest.widget.pictureList.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal sealed class PictureListCallbackAction : ViewAction {
    data class OnInitialSizeMeasured(val width: Int, val height: Int, val horizontalMarginsSum: Int, val verticalMarginsSum: Int) : PictureListCallbackAction()

    class OnAlmostScrolledToVeryBottom() : PictureListCallbackAction()

    data class OnImageClicked(val state: SinglePictureViewState) : PictureListCallbackAction()
}