package com.knowre.android.codilitytest.knowRedux.logger

import android.util.Log
import com.google.gson.GsonBuilder
import com.knowre.android.codilitytest.BuildConfig
import com.knowre.android.codilitytest.knowRedux.StateType
import java.lang.reflect.Modifier


internal class BasicGsonStateLogger<T : StateType>(private val prefix: String = "") : StateLoggerApi<T> {

    private val tag = "$prefix StateLogger"

    private val gson = GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()

    override fun log(oldState: T, newState: T, action: Any) {
        if (BuildConfig.DEBUG) {
            val split = action::class.java.canonicalName.split(action::class.java.`package`.name + ".")

            val normalizedActionName = if (split.size > 1) {
                split[1]
            } else {
                action::class.java.simpleName
            }

            val actionNormalizedName by lazy(LazyThreadSafetyMode.NONE) { "┌─ ✦ $normalizedActionName ✦" }
            val actionCanonicalName  by lazy(LazyThreadSafetyMode.NONE) { "{\"action\"      : \"${action::class.java.canonicalName}\"}" }
            val actionStateJson      by lazy(LazyThreadSafetyMode.NONE) { "{\"actionState\" : ${gson.toJson(action)}}" }
            val oldStateJson         by lazy(LazyThreadSafetyMode.NONE) { "{\"oldState\"    : ${gson.toJson(oldState)}}" }
            val newStateJson         by lazy(LazyThreadSafetyMode.NONE) { "{\"newState\"    : ${gson.toJson(newState)}}" }
            val suffixJson           by lazy(LazyThreadSafetyMode.NONE) { "└─────────────────────────────────────────────────────────────────────────────────────────────────────○" }

            Log.i(tag, actionNormalizedName)
            Log.d(tag, actionCanonicalName)
            Log.d(tag, actionStateJson)
            logLargeString(oldStateJson)

            if (oldState != newState) {
                logLargeString(newStateJson)
            }

            Log.d(tag, suffixJson)
        }
    }

    private fun logLargeString(data: String) {
        val chunkSize = 3500

        var offset = 0

        while (offset + chunkSize <= data.length) {
            Log.d(tag, data.substring(offset, chunkSize.let { offset += it; offset }))
        }

        if (offset < data.length) {
            Log.d(tag, data.substring(offset))
        }
    }

}