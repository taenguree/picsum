package com.knowre.android.codilitytest.persistence.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.knowre.android.codilitytest.persistence.room.IMAGE_CACHE_TABLE


@Entity(tableName = IMAGE_CACHE_TABLE)
internal data class LocalImageEntity(
    @PrimaryKey val id: Int,
                val encoded: String
)