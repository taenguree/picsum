package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import kotlinx.coroutines.*
import javax.inject.Inject


internal class PictureListDataSource @Inject constructor(
              private val api: ImageApi,
              private val coroutineRemote: CoroutineRemoteApi

) : PictureListDataSourceApi {

    companion object { private const val LIMIT = 100 }

    private var isNextPageFetchingInProgress = false

    override suspend fun fetchImageList(page: Int): List<ImageEntity> {
        return coroutineRemote.execute(api.getImageList(page, LIMIT))
    }

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