package com.knowre.android.codilitytest.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.knowre.android.codilitytest.persistence.room.IMAGE_CACHE_TABLE
import com.knowre.android.codilitytest.persistence.room.entity.LocalImageEntity


@Dao
internal interface ImageCacheDao {
    @Query("SELECT * FROM $IMAGE_CACHE_TABLE WHERE id = :id")
    fun getImageEntity(id: Int): LocalImageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageEntity(entity: LocalImageEntity)
}