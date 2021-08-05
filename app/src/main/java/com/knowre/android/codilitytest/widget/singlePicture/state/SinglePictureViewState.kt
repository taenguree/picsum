package com.knowre.android.codilitytest.widget.singlePicture.state

import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal data class SinglePictureViewState(
    val id: Int,
    val width: Int,
    val height: Int,
    val requestedWidth: Int,
    val requestedHeight: Int,
    val url: String,
    val author: String
) : ViewStateType