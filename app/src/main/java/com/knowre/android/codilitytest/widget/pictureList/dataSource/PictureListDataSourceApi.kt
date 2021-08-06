package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity


internal interface PictureListDataSourceApi {
    suspend fun fetchImageList(page: Int): List<ImageEntity>
    suspend fun fetchNextPageImageList(currentPage: Int): List<ImageEntity>?
}