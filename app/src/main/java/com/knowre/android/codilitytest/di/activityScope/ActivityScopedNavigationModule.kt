package com.knowre.android.codilitytest.di.activityScope

import android.content.Context
import com.knowre.android.codilitytest.navigator.Navigator
import com.knowre.android.codilitytest.navigator.NavigatorApi
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSource
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSourceApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ActivityComponent::class)
@Module(includes = [ActivityScopedNavigationModule.ProvideModule::class])
internal interface ActivityScopedNavigationModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {}

    @Binds
    fun provideNavigator(navigator: Navigator): NavigatorApi
}