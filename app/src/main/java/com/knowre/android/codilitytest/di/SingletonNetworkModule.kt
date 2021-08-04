package com.knowre.android.codilitytest.di

import com.google.gson.Gson
import com.knowre.android.codilitytest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module(includes = [SingletonNetworkModule.ProvideModule::class])
internal interface SingletonNetworkModule {
    companion object {
        private const val BASE_URL = "https://picsum.photos"
    }

    @InstallIn(SingletonComponent::class)
    @Module
    object ProvideModule {
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val timeout = 10L

            val builder = OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .addNetworkInterceptor(httpLoggingInterceptor)

            return builder.build()
        }

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        }
    }
}