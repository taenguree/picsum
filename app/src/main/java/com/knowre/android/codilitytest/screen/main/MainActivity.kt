package com.knowre.android.codilitytest.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.knowre.android.codilitytest.databinding.ActivityMainBinding
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.http.ImageApi
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var bindings: ActivityMainBinding

    @Inject lateinit var api: ImageApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        api.getImageList(0, 100).enqueue(object : Callback<List<ImageEntity>?> {
            override fun onResponse(call: Call<List<ImageEntity>?>, response: Response<List<ImageEntity>?>) {
                bindings.customPictureList.render(PictureListViewState(listOf()), PictureListRenderAction.AppendPictures(
                    singlePictureStates = response.body()!!.map {
                        SinglePictureViewState(
                            id     = it.id,
                            width  = it.width,
                            height = it.height,
                            url    = it.downloadUrl,
                            author = it.author
                        )
                    }
                ))
            }

            override fun onFailure(call: Call<List<ImageEntity>?>, t: Throwable) {

            }
        })

    }

}