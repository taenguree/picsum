package com.knowre.android.codilitytest.di.singleton

import android.content.Context
import android.preference.Preference
import android.preference.PreferenceManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.knowre.android.codilitytest.BuildConfig
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.retry.RetryPolicy
import com.knowre.android.codilitytest.http.retry.RetryPolicyApi
import com.knowre.android.codilitytest.persistence.ImageCachePreference
import com.knowre.android.codilitytest.persistence.PreferenceApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [SingletonPreferenceModule.ProvideModule::class])
internal interface SingletonPreferenceModule {
    @InstallIn(SingletonComponent::class)
    @Module
    object ProvideModule {
        @Provides
        @Singleton
        fun providePreference(@ApplicationContext context: Context): PreferenceApi<String> {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)

            return ImageCachePreference(preference = preference, editor = preference.edit())
        }

    }
}