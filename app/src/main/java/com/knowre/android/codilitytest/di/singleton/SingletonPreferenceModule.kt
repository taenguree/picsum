package com.knowre.android.codilitytest.di.singleton

import android.content.Context
import androidx.room.Room
import com.knowre.android.codilitytest.di.qualifier.Database
import com.knowre.android.codilitytest.persistence.DataBasePersistence
import com.knowre.android.codilitytest.persistence.PersistenceApi
import com.knowre.android.codilitytest.persistence.room.DB_NAME
import com.knowre.android.codilitytest.persistence.room.database.PicsumDatabase
import com.knowre.android.codilitytest.persistence.room.entity.LocalImageEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [SingletonPreferenceModule.ProvideModule::class])
internal interface SingletonPreferenceModule {
    @InstallIn(SingletonComponent::class)
    @Module
    object ProvideModule {
        @Provides
        @Singleton
        @Database
        fun providePersistence(dataBase: PicsumDatabase): PersistenceApi<Int, LocalImageEntity> {
            return DataBasePersistence(dataBase)
        }

        @Provides
        @Singleton
        fun providePictureDatabase(@ApplicationContext context: Context): PicsumDatabase {
            return Room.databaseBuilder(context, PicsumDatabase::class.java, DB_NAME).build()
        }
    }
}