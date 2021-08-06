package com.knowre.android.codilitytest.widget.pictureList.view.dto

import com.knowre.android.codilitytest.knowRedux.ViewStateType
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal data class PictureListViewState(
    val width: Int = 0,
    val height: Int = 0,
    val horizontalMarginSum: Int = 0,
    val pictures: List<SinglePictureViewState> = listOf()

) : ViewStateType