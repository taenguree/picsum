package com.knowre.android.codilitytest.di.viewModelScope

import com.knowre.android.codilitytest.http.coroutine.CoroutineRemote
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofit
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofitApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module(includes = [ViewModelScopedNetworkModule.ProvideModule::class])
internal interface ViewModelScopedNetworkModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {}

    @Binds
    @ViewModelScoped
    fun provideCoroutineRetrofit(retrofit: CoroutineRetrofit): CoroutineRetrofitApi

    @Binds
    @ViewModelScoped
    fun provideCoroutineRemote(remote: CoroutineRemote): CoroutineRemoteApi
}