package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.base.CoroutineViewModel
import com.knowre.android.codilitytest.di.qualifier.PictureListIo
import com.knowre.android.codilitytest.widget.io.dto.IoAction
import com.knowre.android.codilitytest.widget.io.dto.IoState
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
                       val mainStateModel: MainStateModel,
                       val pictureListStateModel: BaseStateModel<PictureListViewState, PictureListState, PictureListAction>,
        @PictureListIo val pictureListLoadIoModule: BaseStateModel<IoViewState, IoState, IoAction>

) : CoroutineViewModel(mainStateModel, pictureListStateModel, pictureListLoadIoModule)