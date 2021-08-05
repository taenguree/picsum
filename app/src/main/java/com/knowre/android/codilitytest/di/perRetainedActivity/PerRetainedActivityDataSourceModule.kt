package com.knowre.android.codilitytest.di.perRetainedActivity

import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSource
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSourceApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent


@InstallIn(ActivityRetainedComponent::class)
@Module(includes = [PerRetainedActivityDataSourceModule.ProvideModule::class])
internal interface PerRetainedActivityDataSourceModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {}

    @Binds
    fun providePictureListDataSource(dataSource: PictureListDataSource): PictureListDataSourceApi
}