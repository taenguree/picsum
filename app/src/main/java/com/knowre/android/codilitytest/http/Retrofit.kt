package com.knowre.android.codilitytest.http

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal object Retrofit {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://picsum.photos")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    val api = retrofit.create(ImageApi::class.java)

}