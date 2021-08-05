package com.knowre.android.codilitytest.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.knowre.android.codilitytest.databinding.ActivityMainBinding
import com.knowre.android.codilitytest.widget.io.IoRenderer
import com.knowre.android.codilitytest.widget.pictureList.PictureListRenderer
import com.knowre.android.codilitytest.widget.pictureList.view.PictureListIoView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        viewModel.pictureListStateModel.setRenderer(PictureListRenderer(bindings.customPictureList))
        viewModel.pictureListLoadIoModule.setRenderer(IoRenderer(PictureListIoView(bindings.customPictureList.binding.pbProgress)))

        viewModel.onCreate(savedInstanceState)
    }

}