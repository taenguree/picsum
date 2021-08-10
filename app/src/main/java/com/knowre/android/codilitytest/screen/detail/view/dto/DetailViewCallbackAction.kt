package com.knowre.android.codilitytest.screen.detail.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction


internal sealed class DetailViewCallbackAction : ViewAction {
    data class OnInitialSizeMeasured(val width: Int) : DetailViewCallbackAction()

    data class OnGrayScaleClicked(val width: Int) : DetailViewCallbackAction()

    data class OnBlurClicked(val width: Int) : DetailViewCallbackAction()

    class OnPreviousClicked(val currentImageId: Int) : DetailViewCallbackAction()

    class OnNextClicked(val currentImageId: Int) : DetailViewCallbackAction()
}