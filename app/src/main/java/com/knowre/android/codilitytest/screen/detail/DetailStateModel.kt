package com.knowre.android.codilitytest.screen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.detail.dataSource.DetailDataSourceApi
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import com.knowre.android.codilitytest.glide.ImageBinderFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


internal class DetailStateModel @Inject constructor(
    @ApplicationContext private val context: Context,
                        private val dataSource: DetailDataSourceApi

) : LifecycleAwareStateModel<DetailViewState, DetailState, DetailAction>(
    initialState = DetailState(),
    reducer      = DetailReducer(),
    middleware   = LoggingMiddleware("DET")
) {

    override fun onCreate(savedInstanceState: Bundle?, intent: Intent?) {
        super.onCreate(savedInstanceState, intent)

        launch { dispatch(DetailAction.Input.IntentData(intent!!.getSerializableExtra(DetailActivity.INTENT_EXTRA_NAME) as DetailIntentData)) }
    }

    override suspend fun onNext(state: DetailState, action: DetailAction) {
        super.onNext(state, action)

        when(action) {
            is DetailAction.Callback -> when(val callbackAction = action.action) {
                is DetailViewCallbackAction.OnInitialSizeMeasured -> {
                    dispatch(
                        DetailAction.Render(
                            DetailViewRenderAction.Render(
                                dataSource.fetchViewState(
                                    id                  = getState().intentData.clickedPictureId,
                                    url                 = getState().intentData.clickedPictureUrl,
                                    author              = getState().intentData.author,
                                    viewWidth           = callbackAction.width,
                                    imageOriginalWidth  = getState().intentData.imageOriginalWidth,
                                    imageOriginalHeight = getState().intentData.imageOriginalHeight,
                                    isGrayScale         = false,
                                    isBlur              = false,
                                    scope               = getScope()!!
                                )
                            )
                        )
                    )
                }

                is DetailViewCallbackAction.OnGrayScaleClicked -> {
                    dispatch(
                        DetailAction.Render(
                            DetailViewRenderAction.Render(
                                dataSource.fetchViewState(
                                    id                  = getState().viewState.id,
                                    url                 = getState().viewState.url,
                                    author              = getState().viewState.author,
                                    viewWidth           = callbackAction.width,
                                    imageOriginalWidth  = getState().viewState.imageOriginalWidth,
                                    imageOriginalHeight = getState().viewState.imageOriginalHeight,
                                    isGrayScale         = true,
                                    isBlur              = false,
                                    scope               = getScope()!!
                                )
                            )
                        )
                    )
                }

                is DetailViewCallbackAction.OnBlurClicked -> {
                    dispatch(
                        DetailAction.Render(
                            DetailViewRenderAction.Render(
                                dataSource.fetchViewState(
                                    id                  = getState().viewState.id,
                                    url                 = getState().viewState.url,
                                    author              = getState().viewState.author,
                                    viewWidth           = callbackAction.width,
                                    imageOriginalWidth  = getState().viewState.imageOriginalWidth,
                                    imageOriginalHeight = getState().viewState.imageOriginalHeight,
                                    isGrayScale         = false,
                                    isBlur              = true,
                                    scope               = getScope()!!
                                )
                            )
                        )
                    )
                }

                is DetailViewCallbackAction.OnNextClicked -> {
                    val nextViewState = dataSource.fetchNextImageEntity(callbackAction.currentImageId, state.pictureViewWidth, getScope()!!)

                    if (nextViewState == null) {
                        dispatch(DetailAction.Render(DetailViewRenderAction.ShowNoMorePictureToast(message = context.getString(R.string.last_picture))))
                    } else {
                        dispatch(DetailAction.Render(DetailViewRenderAction.Render(nextViewState)))
                    }
                }

                is DetailViewCallbackAction.OnPreviousClicked -> {
                    val previousViewState = dataSource.fetchPreviousImageEntity(callbackAction.currentImageId, state.pictureViewWidth, getScope()!!)

                    if (previousViewState == null) {
                        dispatch(DetailAction.Render(DetailViewRenderAction.ShowNoMorePictureToast(message = context.getString(R.string.first_picture))))
                    } else {
                        dispatch(DetailAction.Render(DetailViewRenderAction.Render(previousViewState)))
                    }
                }
            }

            else -> Unit
        }
    }

}