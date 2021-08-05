package com.knowre.android.codilitytest.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.system.measureTimeMillis


internal class Base64Encoder @Inject constructor() {

    fun encode(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        val b: ByteArray = outputStream.toByteArray()

        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decode(input: String): Bitmap {
        val decodedByte = Base64.decode(input, 0)

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }

}