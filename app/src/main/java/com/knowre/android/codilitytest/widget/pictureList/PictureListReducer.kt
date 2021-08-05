package com.knowre.android.codilitytest.widget.pictureList

import com.knowre.android.codilitytest.extensions.swapOrAdd
import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListRenderAction


internal class PictureListReducer : Reducer<PictureListState, PictureListAction> {

    override fun reduce(state: PictureListState, action: PictureListAction): PictureListState {
        return when (action) {
            is PictureListAction.Render -> when (val renderAction = action.action) {
                is PictureListRenderAction.AppendPictures -> state.copy(viewState = state.viewState.copy(pictures = state.viewState.pictures.swapOrAdd(renderAction.singlePictureStates) { old, new -> old.id == new.id }))
                    .run { if (renderAction.singlePictureStates.isEmpty()) { this.copy(isNoMorePictureExist = true) } else  { this } }

                else -> state
            }

            is PictureListAction.Callback -> when (val callbackAction = action.action) {
                is PictureListCallbackAction.OnInitialSizeMeasured -> state.copy(viewState = state.viewState.copy(width = callbackAction.width, height = callbackAction.height))

                else -> state
            }
        }
    }

}