package com.knowre.android.codilitytest.store.persistence.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.knowre.android.codilitytest.store.persistence.room.dao.ImageCacheDao
import com.knowre.android.codilitytest.store.persistence.room.entity.LocalImageEntity


@Database(entities = [LocalImageEntity::class], version = 1, exportSchema = false)
internal abstract class PicsumDatabase : RoomDatabase() {

    abstract fun imageCacheDao(): ImageCacheDao

}