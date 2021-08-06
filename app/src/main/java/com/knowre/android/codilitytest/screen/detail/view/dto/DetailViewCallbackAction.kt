package com.knowre.android.codilitytest.screen.detail.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction


internal sealed class DetailViewCallbackAction : ViewAction {
    class OnInitialSizeMeasured(val width: Int) : DetailViewCallbackAction()

    class OnGrayScaleClicked(val width: Int) : DetailViewCallbackAction()

    class OnBlurClicked(val width: Int) : DetailViewCallbackAction()
}