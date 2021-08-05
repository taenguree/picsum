package com.knowre.android.codilitytest.widget.pictureList

import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState


internal class PictureListReducer : Reducer<PictureListState, PictureListAction> {

    override fun reduce(state: PictureListState, action: PictureListAction): PictureListState {
        return state
    }

}