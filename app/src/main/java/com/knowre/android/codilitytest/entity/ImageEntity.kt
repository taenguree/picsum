package com.knowre.android.codilitytest.entity

import com.google.gson.annotations.SerializedName


internal data class ImageEntity(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String

) {

    fun getRatio() = width.toFloat() / height.toFloat()

}