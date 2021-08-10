package com.knowre.android.codilitytest.dataSource

import com.knowre.android.codilitytest.base.PageNumber
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.entity.PagedImageEntity
import com.knowre.android.codilitytest.store.memoryCache.MemoryStoreApi


internal interface ImageDataSourceApi : MemoryStoreApi<PageNumber, PagedImageEntity> {
    suspend fun fetchImageList(pageNumber: PageNumber): List<ImageEntity>
}