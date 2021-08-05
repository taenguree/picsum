package com.knowre.android.codilitytest.widget.pictureList.view.state

import com.knowre.android.codilitytest.knowRedux.ViewStateType
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal data class PictureListViewState(
    val pictures: List<SinglePictureViewState> = listOf()

) : ViewStateType