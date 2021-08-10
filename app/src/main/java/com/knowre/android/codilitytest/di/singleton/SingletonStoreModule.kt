package com.knowre.android.codilitytest.di.singleton

import android.content.Context
import androidx.room.Room
import com.knowre.android.codilitytest.base.PageNumber
import com.knowre.android.codilitytest.entity.PagedImageEntity
import com.knowre.android.codilitytest.store.memoryCache.PagedImageEntityMemoryStore
import com.knowre.android.codilitytest.store.memoryCache.MemoryStoreApi
import com.knowre.android.codilitytest.store.persistence.DatabaseStore
import com.knowre.android.codilitytest.store.persistence.PersistenceStoreApi
import com.knowre.android.codilitytest.store.persistence.room.DB_NAME
import com.knowre.android.codilitytest.store.persistence.room.database.PicsumDatabase
import com.knowre.android.codilitytest.store.persistence.room.entity.LocalImageEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [SingletonStoreModule.ProvideModule::class])
internal interface SingletonStoreModule {
    @InstallIn(SingletonComponent::class)
    @Module
    object ProvideModule {
        @Provides
        @Singleton
        fun provideDatabaseStore(dataBase: PicsumDatabase): PersistenceStoreApi<PageNumber, LocalImageEntity> {
            return DatabaseStore(dataBase)
        }

        @Provides
        @Singleton
        fun providePictureDatabase(@ApplicationContext context: Context): PicsumDatabase {
            return Room.databaseBuilder(context, PicsumDatabase::class.java, DB_NAME).build()
        }

        @Provides
        @Singleton
        fun provideMemoryStore(memoryStore: PagedImageEntityMemoryStore): MemoryStoreApi<PageNumber, PagedImageEntity> {
            return PagedImageEntityMemoryStore()
        }
    }

}