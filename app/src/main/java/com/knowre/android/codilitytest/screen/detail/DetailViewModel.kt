package com.knowre.android.codilitytest.screen.detail

import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.base.CoroutineViewModel
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class DetailViewModel @Inject constructor(
    val detailStateModel: BaseStateModel<DetailViewState, DetailState, DetailAction>

) : CoroutineViewModel(detailStateModel)