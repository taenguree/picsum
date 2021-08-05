package com.knowre.android.codilitytest.widget.pictureList.dto

import com.knowre.android.codilitytest.knowRedux.StateType
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.widget.pictureList.view.PictureListView
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState


internal data class PictureListState(
    override val viewState: PictureListViewState = PictureListViewState()

) : StateType, ViewStateAware<PictureListViewState>