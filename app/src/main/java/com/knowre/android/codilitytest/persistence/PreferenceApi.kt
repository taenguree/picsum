package com.knowre.android.codilitytest.persistence

import kotlinx.coroutines.flow.Flow


internal interface PreferenceApi<T> {
    fun get(key: String): String
    fun put(key: String, value: T)
}