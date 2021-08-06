package com.knowre.android.codilitytest.screen.detail.dto

import java.io.Serializable


internal data class DetailIntentData(
    val clickedPictureId: Int = -1,
    val clickedPictureUrl: String = "",
    val imageOriginalWidth: Int = -1,
    val imageOriginalHeight: Int = -1,
    val author: String = ""
) : Serializable