package com.knowre.android.codilitytest.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.knowre.android.codilitytest.screen.detail.DetailActivity
import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


internal class Navigator @Inject constructor(@ActivityContext private val context: Context) : NavigatorApi {

    override fun navigateToDetailActivity(intentData: DetailIntentData) {
        (context as Activity).startActivity(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.INTENT_EXTRA_NAME, intentData)
            }
        )
    }

}