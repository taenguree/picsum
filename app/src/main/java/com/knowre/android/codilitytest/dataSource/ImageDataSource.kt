package com.knowre.android.codilitytest.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import javax.inject.Inject


internal class ImageDataSource @Inject constructor(
    private val api: ImageApi,
    private val coroutineRemote: CoroutineRemoteApi

): ImageDataSourceApi {

    companion object { private const val LIMIT = 100 }

    override suspend fun fetchImageList(page: Int): List<ImageEntity> {
        return coroutineRemote.execute(api.getImageList(page, LIMIT))
    }

}