package com.knowre.android.codilitytest.dataSource

import com.knowre.android.codilitytest.base.PageNumber
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.entity.PagedImageEntity
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.store.memoryCache.MemoryStoreApi
import com.knowre.android.codilitytest.store.memoryCache.PagedImageEntityMemoryStore
import javax.inject.Inject


internal class ImageDataSource @Inject constructor(
    private val api: ImageApi,
    private val coroutineRemote: CoroutineRemoteApi,
    private val pagedImageEntityMemoryStore: MemoryStoreApi<@JvmSuppressWildcards PageNumber, PagedImageEntity>

): ImageDataSourceApi, MemoryStoreApi<PageNumber, PagedImageEntity> by pagedImageEntityMemoryStore {

    companion object { private const val LIMIT = 100 }

    override suspend fun fetchImageList(pageNumber: PageNumber): List<ImageEntity> {
        return pagedImageEntityMemoryStore.get(key = pageNumber)?.entities
            ?: coroutineRemote.execute(api.getImageList(pageNumber, LIMIT)).also {
                pagedImageEntityMemoryStore.put(pageNumber, PagedImageEntity(pageNumber, it))
            }
    }

}