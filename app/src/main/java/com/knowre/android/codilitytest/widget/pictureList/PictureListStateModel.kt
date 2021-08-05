package com.knowre.android.codilitytest.widget.pictureList

import android.util.Log
import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSourceApi
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState
import javax.inject.Inject


internal class PictureListStateModel @Inject constructor(
    private val dataSource: PictureListDataSourceApi

) : LifecycleAwareStateModel<PictureListViewState, PictureListState, PictureListAction>(
    initialState = PictureListState(),
    reducer      = PictureListReducer(),
    middleware   = LoggingMiddleware("PIL")
) {

    override suspend fun onNext(state: PictureListState, action: PictureListAction) {
        super.onNext(state, action)

        when (action) {
            is PictureListAction.Callback -> when (val callbackAction = action.action) {
                is PictureListCallbackAction.OnInitialSizeMeasured -> {
                    val viewStates = dataSource.fetchImageList().map {
                        val ratio = it.width.toFloat() / it.height.toFloat()

                        val requestedWidth  = callbackAction.width/2
                        val requestedHeight = (requestedWidth/ratio).toInt()

                        SinglePictureViewState(
                            id              = it.id,
                            width           = it.width,
                            height          = it.height,
                            requestedWidth  = requestedWidth,
                            requestedHeight = requestedHeight,
                            url             = it.downloadUrl,
                            author          = it.author
                        )
                    }

                    dispatch(PictureListAction.Render(PictureListRenderAction.AppendPictures(viewStates)))
                }
            }

            else -> Unit
        }
    }

}