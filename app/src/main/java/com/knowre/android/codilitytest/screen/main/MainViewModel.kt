package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.base.CoroutineViewModel
import com.knowre.android.codilitytest.widget.pictureList.PictureListStateModel
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
        val mainStateModel: MainStateModel,
        val pictureListStateModel: BaseStateModel<PictureListViewState, PictureListState, PictureListAction>

) : CoroutineViewModel(pictureListStateModel)