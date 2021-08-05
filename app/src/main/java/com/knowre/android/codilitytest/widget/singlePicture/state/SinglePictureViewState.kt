package com.knowre.android.codilitytest.widget.singlePicture.state

import android.widget.ImageView
import com.knowre.android.codilitytest.knowRedux.ViewStateType


typealias ImageBinder = (
    view: ImageView,
    id: Int,
    url: String,
    requestedWidth: Int,
    requestedHeight: Int,
    onStart: () -> Unit,
    onComplete:() -> Unit
) -> Unit

internal data class SinglePictureViewState(
    val id: Int,
    val width: Int,
    val height: Int,
    val requestedWidth: Int,
    val requestedHeight: Int,
    val url: String,
    val author: String,
    val imageBinder: ImageBinder
) : ViewStateType