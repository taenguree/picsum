package com.knowre.android.codilitytest.screen.detail.dataSource

import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import javax.inject.Inject


internal class DetailDataSource @Inject constructor(
    private val imageDataSource: ImageDataSourceApi

) : DetailDataSourceApi, ImageDataSourceApi by imageDataSource {



}