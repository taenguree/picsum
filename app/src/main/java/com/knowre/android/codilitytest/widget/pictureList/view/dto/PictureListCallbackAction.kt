package com.knowre.android.codilitytest.widget.pictureList.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction


internal sealed class PictureListCallbackAction : ViewAction {
    class OnInitialSizeMeasured(val width: Int, val height: Int, val horizontalMarginsSum: Int, val verticalMarginsSum: Int) : PictureListCallbackAction()
    class OnAlmostScrolledToVeryBottom(val totalItemCount: Int) : PictureListCallbackAction()
}