package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData
import com.knowre.android.codilitytest.screen.main.dto.MainAction
import com.knowre.android.codilitytest.screen.main.dto.MainState
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewState
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState
import javax.inject.Inject


internal class MainStateModel @Inject constructor(
    private val pictureListStateModel: BaseStateModel<PictureListViewState, PictureListState, PictureListAction>

) : LifecycleAwareStateModel<MainViewState, MainState, MainAction>(
    initialState = MainState(),
    reducer      = MainReducer(),
    middleware   = LoggingMiddleware()
) {

    init {
        initializeListener()
    }

    private fun initializeListener() {
        pictureListStateModel.setListener(object : Listener<PictureListAction> {
            override suspend fun onPreAction(action: PictureListAction) = Unit

            override suspend fun onPostAction(action: PictureListAction) {
                when (action) {
                    is PictureListAction.Callback -> when(val callbackAction = action.action) {
                        is PictureListCallbackAction.OnImageClicked -> {
                            navigator!!.navigateToDetailActivity(
                                DetailIntentData(
                                    clickedPictureId    = callbackAction.state.id,
                                    clickedPictureUrl   = callbackAction.state.url,
                                    imageOriginalWidth  = callbackAction.state.imageOriginalWidth,
                                    imageOriginalHeight = callbackAction.state.imageOriginalHeight,
                                    author              = callbackAction.state.author
                                )
                            )
                        }
                    }

                    else -> Unit
                }
            }
        })
    }

}