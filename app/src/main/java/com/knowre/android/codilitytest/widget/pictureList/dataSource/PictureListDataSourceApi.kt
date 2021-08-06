package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import com.knowre.android.codilitytest.entity.ImageEntity


internal interface PictureListDataSourceApi : ImageDataSourceApi {
    suspend fun fetchNextPageImageList(currentPage: Int): List<ImageEntity>?
}