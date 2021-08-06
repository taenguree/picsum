package com.knowre.android.codilitytest.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.knowre.android.codilitytest.databinding.ActivityMainBinding
import com.knowre.android.codilitytest.navigator.NavigatorApi
import com.knowre.android.codilitytest.widget.io.IoRenderer
import com.knowre.android.codilitytest.widget.pictureList.PictureListRenderer
import com.knowre.android.codilitytest.widget.io.view.BasicIoView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigator: NavigatorApi

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        viewModel.mainStateModel.setRenderer(MainRenderer())
        viewModel.mainStateModel.setNavigator(navigator)

        viewModel.pictureListStateModel.setRenderer(PictureListRenderer(bindings.customMain.binding.customPictureList))
        viewModel.pictureListLoadIoModule.setRenderer(IoRenderer(BasicIoView(bindings.customMain.binding.customPictureList.binding.pbProgress, this)))

        viewModel.onCreate(savedInstanceState)
    }

}