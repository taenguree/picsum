package com.knowre.android.codilitytest.screen.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.knowre.android.codilitytest.databinding.ActivityDetailBinding


internal class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.detailStateModel.setRenderer(DetailRenderer(binding.customDetail))

        viewModel.onCreate(savedInstanceState)
    }

}