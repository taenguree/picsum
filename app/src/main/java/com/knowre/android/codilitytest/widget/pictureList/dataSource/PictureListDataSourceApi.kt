package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity


internal interface PictureListDataSourceApi {
    suspend fun fetchImageList(): List<ImageEntity>
}