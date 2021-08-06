package com.knowre.android.codilitytest.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity


internal interface ImageDataSourceApi {
    suspend fun fetchImageList(page: Int): List<ImageEntity>
}