package com.knowre.android.codilitytest.widget.pictureList.dto

import com.knowre.android.codilitytest.knowRedux.StateType
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState


internal data class PictureListState(
    override val viewState: PictureListViewState = PictureListViewState(),
    val isNoMorePictureExist: Boolean = false

) : StateType, ViewStateAware<PictureListViewState>