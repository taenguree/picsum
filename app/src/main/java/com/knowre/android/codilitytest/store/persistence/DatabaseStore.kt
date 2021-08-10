package com.knowre.android.codilitytest.store.persistence

import com.knowre.android.codilitytest.store.persistence.room.database.PicsumDatabase
import com.knowre.android.codilitytest.store.persistence.room.entity.LocalImageEntity


internal class DatabaseStore constructor(
    private val database: PicsumDatabase

) : PersistenceStoreApi<Int, LocalImageEntity> {

    override fun getAll(): List<LocalImageEntity> {
        return database.imageCacheDao().getImageEntities()
    }

    override fun get(key: Int): LocalImageEntity? {
        return database.imageCacheDao().getImageEntity(key).firstOrNull()
    }

    override fun put(key: Int, value: LocalImageEntity) {
        return database.imageCacheDao().insertImageEntity(value)
    }

}