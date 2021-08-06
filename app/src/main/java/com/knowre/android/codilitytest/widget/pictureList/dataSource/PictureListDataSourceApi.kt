package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.widget.singlePicture.state.ImageBinder
import kotlinx.coroutines.CoroutineScope


internal interface PictureListDataSourceApi {
    suspend fun fetchImageList(page: Int): List<ImageEntity>
    suspend fun fetchNextPageImageList(currentPage: Int): List<ImageEntity>?
}