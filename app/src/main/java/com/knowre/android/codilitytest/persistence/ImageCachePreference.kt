package com.knowre.android.codilitytest.persistence

import android.content.SharedPreferences
import javax.inject.Inject


internal class ImageCachePreference @Inject constructor(
    private val preference: SharedPreferences,
    private val editor: SharedPreferences.Editor

) : PreferenceApi<String> {

    override fun get(key: String): String {
        return preference.getString(key, "") ?: ""
    }

    override fun put(key: String, value: String) {
        editor.run {
            putString(key, value)

            commit()
        }
    }

}