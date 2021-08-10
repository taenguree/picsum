package com.knowre.android.codilitytest.store.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.knowre.android.codilitytest.store.persistence.room.IMAGE_CACHE_TABLE
import com.knowre.android.codilitytest.store.persistence.room.entity.LocalImageEntity


@Dao
internal interface ImageCacheDao {
    @Query("SELECT * FROM $IMAGE_CACHE_TABLE")
    fun getImageEntities(): List<LocalImageEntity>

    @Query("SELECT * FROM $IMAGE_CACHE_TABLE WHERE id = :id")
    fun getImageEntity(id: Int): List<LocalImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageEntity(entity: LocalImageEntity)
}