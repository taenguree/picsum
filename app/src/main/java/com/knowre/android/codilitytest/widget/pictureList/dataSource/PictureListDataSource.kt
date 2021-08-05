package com.knowre.android.codilitytest.widget.pictureList.dataSource

import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import javax.inject.Inject


internal class PictureListDataSource @Inject constructor(
    private val api: ImageApi,
    private val coroutineRemote: CoroutineRemoteApi

) : PictureListDataSourceApi {

    override suspend fun fetchImageList(): List<ImageEntity> {
        return coroutineRemote.execute(api.getImageList(0, 100))
    }

}