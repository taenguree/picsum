package com.knowre.android.codilitytest.screen.detail.view.dto

import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal data class DetailViewState(
               val id: Int = -1,
               val url: String = "",
               val author: String = "",
               val imageOriginalWidth: Int = -1,
               val imageOriginalHeight: Int = -1,
               val requestedWidth: Int = -1,
               val requestedHeight: Int = -1,
               val isGrayScale: Boolean = false,
               val isBlur: Boolean = false,
               val setImage: ImageBinder = { _, _, _, _, _, _, _, _, _ ->  }
) : ViewStateType