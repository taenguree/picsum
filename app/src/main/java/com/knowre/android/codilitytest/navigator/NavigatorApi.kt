package com.knowre.android.codilitytest.navigator

import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData


internal interface NavigatorApi {
    fun navigateToDetailActivity(intentData: DetailIntentData)
}