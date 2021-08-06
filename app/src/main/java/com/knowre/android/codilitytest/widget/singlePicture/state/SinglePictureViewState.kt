package com.knowre.android.codilitytest.widget.singlePicture.state

import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal data class SinglePictureViewState(
    val id: Int,
    val imageOriginalWidth: Int,
    val imageOriginalHeight: Int,
    val requestedWidth: Int,
    val requestedHeight: Int,
    val url: String,
    val author: String,
    val setImage: ImageBinder
) : ViewStateType