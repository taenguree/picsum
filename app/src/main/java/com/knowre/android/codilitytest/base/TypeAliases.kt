package com.knowre.android.codilitytest.base

import android.widget.ImageView


typealias ImageBinder = (
    view: ImageView,
    id: Int,
    url: String,
    useCache: Boolean,
    requestedWidth: Int,
    requestedHeight: Int,
    onStart: () -> Unit,
    onComplete:() -> Unit
) -> Unit