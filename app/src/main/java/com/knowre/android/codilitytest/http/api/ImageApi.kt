package com.knowre.android.codilitytest.http.api

import androidx.annotation.ChecksSdkIntAtLeast
import com.knowre.android.codilitytest.entity.ImageEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


internal interface ImageApi {
    @GET("/v2/list")
    fun getImageList(@Query("page") page: Int, @Query("limit") limit: Int): Call<List<ImageEntity>>
}