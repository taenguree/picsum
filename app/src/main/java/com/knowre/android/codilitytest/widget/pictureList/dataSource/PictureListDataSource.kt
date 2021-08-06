package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import com.knowre.android.codilitytest.entity.ImageEntity
import kotlinx.coroutines.*
import javax.inject.Inject


internal class PictureListDataSource @Inject constructor(
    private val imageDataSource: ImageDataSourceApi

) : PictureListDataSourceApi, ImageDataSourceApi by imageDataSource {

    private var isNextPageFetchingInProgress = false

    override suspend fun fetchNextPageImageList(currentPage: Int): List<ImageEntity>? {
        return coroutineScope {
            try {
                if (!isNextPageFetchingInProgress) {
                    isNextPageFetchingInProgress = true
                    fetchImageList(currentPage + 1)
                } else {
                    null
                }
            } finally {
                isNextPageFetchingInProgress = false
            }
        }
    }

}