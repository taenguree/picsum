package com.knowre.android.codilitytest.di.perRetainedActivity

import com.knowre.android.codilitytest.http.coroutine.CoroutineRemote
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofit
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofitApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent


@InstallIn(ActivityRetainedComponent::class)
@Module(includes = [PerRetainedActivityNetworkModule.ProvideModule::class])
internal interface PerRetainedActivityNetworkModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {}

    @Binds
    fun provideCoroutineRetrofit(retrofit: CoroutineRetrofit): CoroutineRetrofitApi

    @Binds
    fun provideCoroutineRemote(remote: CoroutineRemote): CoroutineRemoteApi
}