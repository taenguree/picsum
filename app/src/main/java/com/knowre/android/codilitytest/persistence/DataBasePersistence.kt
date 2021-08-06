package com.knowre.android.codilitytest.persistence

import com.knowre.android.codilitytest.persistence.room.database.PicsumDatabase
import com.knowre.android.codilitytest.persistence.room.entity.LocalImageEntity


internal class DataBasePersistence constructor(
    private val database: PicsumDatabase

) : PersistenceApi<Int, LocalImageEntity> {

    override fun get(key: Int): LocalImageEntity? {
        return database.imageCacheDao().getImageEntity(key)
    }

    override fun put(key: Int, value: LocalImageEntity) {
        return database.imageCacheDao().insertImageEntity(value)
    }

}