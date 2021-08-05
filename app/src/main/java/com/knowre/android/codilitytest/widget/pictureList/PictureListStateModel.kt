package com.knowre.android.codilitytest.widget.pictureList

import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.widget.pictureList.dataSource.PictureListDataSourceApi
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.mapper.SinglePictureViewStateMapper
import com.knowre.android.codilitytest.widget.pictureList.view.PictureListView
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState
import javax.inject.Inject


internal class PictureListStateModel @Inject constructor(
    private val dataSource: PictureListDataSourceApi,
    private val singlePictureViewStateMapper: SinglePictureViewStateMapper

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
                    val viewStates = dataSource.fetchImageList().map { singlePictureViewStateMapper.transform(it, callbackAction.width, PictureListView.COLUMN_COUNT, dataSource.getImageBinder(getScope()!!)) }
                    dispatch(PictureListAction.Render(PictureListRenderAction.AppendPictures(viewStates)))
                }

                is PictureListCallbackAction.OnAlmostScrolledToVeryBottom -> {
                    if (!state.isNoMorePictureExist) {
                        val viewStates = dataSource.fetchNextPageImageList()?.map { singlePictureViewStateMapper.transform(it, state.viewState.width, PictureListView.COLUMN_COUNT, dataSource.getImageBinder(getScope()!!)) }

                        viewStates?.let {
                            dispatch(PictureListAction.Render(PictureListRenderAction.AppendPictures(viewStates)))
                            dispatch(PictureListAction.Render(PictureListRenderAction.ShowAppendToast(isNoMorePictureExists = viewStates.isEmpty())))
                        }
                    }
                }
            }

            else -> Unit
        }
    }

}