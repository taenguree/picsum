package com.knowre.android.codilitytest.di.viewModelScope

import com.knowre.android.codilitytest.dataSource.ImageDataSource
import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import com.knowre.android.codilitytest.screen.detail.dataSource.DetailDataSource
import com.knowre.android.codilitytest.screen.detail.dataSource.DetailDataSourceApi
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSource
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSourceApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module(includes = [ViewModelScopedDataSourceModule.ProvideModule::class])
internal interface ViewModelScopedDataSourceModule {
    @InstallIn(ViewModelComponent::class)
    @Module
    object ProvideModule {}

    @Binds
    @ViewModelScoped
    fun providePictureListDataSource(dataSource: PictureListDataSource): PictureListDataSourceApi

    @Binds
    @ViewModelScoped
    fun provideImageDataSource(dataSource: ImageDataSource): ImageDataSourceApi

    @Binds
    @ViewModelScoped
    fun provideDetailDataSource(dataSource: DetailDataSource): DetailDataSourceApi
}