package com.knowre.android.codilitytest.screen.detail.dataSource

import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import kotlinx.coroutines.CoroutineScope


internal interface DetailDataSourceApi : ImageDataSourceApi {
    fun fetchViewState(id: Int, url: String, author: String, viewWidth: Int, imageOriginalWidth: Int, imageOriginalHeight :Int, isGrayScale: Boolean, isBlur: Boolean, scope: CoroutineScope): DetailViewState
    suspend fun fetchNextImageEntity(currentImageId: Int, viewWidth: Int, scope: CoroutineScope): DetailViewState?
    suspend fun fetchPreviousImageEntity(currentImageId: Int, viewWidth: Int, scope: CoroutineScope): DetailViewState?
}