package com.knowre.android.codilitytest.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


val Context.imageCacheDataSource: DataStore<Preferences> by preferencesDataStore(name = "image_cache_data_source")