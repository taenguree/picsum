package com.knowre.android.codilitytest.di.perRetainedActivity

import com.knowre.android.codilitytest.http.callState.CallStateListenerApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemote
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofit
import com.knowre.android.codilitytest.http.coroutine.CoroutineRetrofitApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Call


@InstallIn(ActivityRetainedComponent::class)
@Module(includes = [PerRetainedActivityNetworkModule.ProvideModule::class])
internal interface PerRetainedActivityNetworkModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {
        @Provides
        fun provideCallStateListener(): CallStateListenerApi<Call<*>> {
            return object : CallStateListenerApi<Call<*>> {
                override fun onInitialCallStarted(call: Call<*>) {
                }

                override fun onRetryingBackoffStarted(call: Call<*>, throwable: Throwable, backOffDelay: Long, retryCount: Int) {
                }

                override fun onRetryCallStarted(call: Call<*>, throwable: Throwable, retryCount: Int) {
                }

                override fun onSucceed(call: Call<*>) {
                }

                override fun onFailure(call: Call<*>, throwable: Throwable, retryCount: Int) {
                }

                override fun onResponseIgnored(call: Call<*>) {
                }
            }
        }
    }

    @Binds
    fun provideCoroutineRetrofit(retrofit: CoroutineRetrofit): CoroutineRetrofitApi

    @Binds
    fun provideCoroutineRemote(remote: CoroutineRemote): CoroutineRemoteApi
}