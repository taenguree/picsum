package com.knowre.android.codilitytest.store.memoryCache

import com.knowre.android.codilitytest.base.PageNumber
import com.knowre.android.codilitytest.entity.PagedImageEntity
import javax.inject.Inject


internal class PagedImageEntityMemoryStore @Inject constructor() : MemoryStoreApi<PageNumber, PagedImageEntity> {

    private var entities = mutableListOf<PagedImageEntity>()

    override fun getAll(): List<PagedImageEntity> = entities

    override fun get(key: PageNumber) = entities.find { it.pageNumber == key }

    override fun put(key: PageNumber, value: PagedImageEntity) {
        val existingEntityIndex = entities.indexOfFirst { it.pageNumber == key }

        if (existingEntityIndex == -1) {
            entities.add(value)
        } else {
            entities[existingEntityIndex] = value
        }

        entities.sortBy { it.pageNumber }
    }

}

