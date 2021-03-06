package com.knowre.android.codilitytest.screen.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.knowre.android.codilitytest.databinding.ActivityDetailBinding
import com.knowre.android.codilitytest.widget.io.IoRenderer
import com.knowre.android.codilitytest.widget.io.view.BasicIoView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class DetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_EXTRA_NAME = "DETAIL_ACTIVITY_INTENT_EXTRA"
    }

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.detailStateModel.setRenderer(DetailRenderer(binding.customDetail))
        viewModel.ioStateModel.setRenderer(IoRenderer(BasicIoView(binding.customDetail.binding.pbProgress, this)))

        viewModel.onCreate(savedInstanceState, intent)
    }

}