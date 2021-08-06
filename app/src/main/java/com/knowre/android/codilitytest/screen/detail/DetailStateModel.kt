package com.knowre.android.codilitytest.screen.detail

import android.content.Intent
import android.os.Bundle
import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailIntentData
import com.knowre.android.codilitytest.screen.detail.dto.DetailState
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import com.knowre.android.codilitytest.widget.pictureList.ImageBinderFactory
import javax.inject.Inject


internal class DetailStateModel @Inject constructor(
    private val imageBinderFactory: ImageBinderFactory

) : LifecycleAwareStateModel<DetailViewState, DetailState, DetailAction>(
    initialState = DetailState(),
    reducer      = DetailReducer(),
    middleware   = LoggingMiddleware("DET")
) {

    override fun onCreate(savedInstanceState: Bundle?, intent: Intent?) {
        super.onCreate(savedInstanceState, intent)

        launch {
            dispatch(DetailAction.Input.IntentData(intent!!.getSerializableExtra(DetailActivity.INTENT_EXTRA_NAME) as DetailIntentData))
        }
    }

    override suspend fun onNext(state: DetailState, action: DetailAction) {
        super.onNext(state, action)

        val imageBinder = imageBinderFactory.getImageBinder(getScope()!!)

        when(action) {
            is DetailAction.Callback -> when(val callbackAction = action.action) {
                is DetailViewCallbackAction.OnInitialSizeMeasured -> dispatch(action(getState().intentData, callbackAction.width, isGrayScale = false, isBlur = false, imageBinder = imageBinder))

                is DetailViewCallbackAction.OnGrayScaleClicked -> dispatch(action(getState().intentData, callbackAction.width, isGrayScale = true, isBlur = false, imageBinder = imageBinder))

                is DetailViewCallbackAction.OnBlurClicked -> dispatch(action(getState().intentData, callbackAction.width, isGrayScale = false, isBlur = true, imageBinder = imageBinder))
            }

            else -> Unit
        }
    }

    private fun action(intentData: DetailIntentData, viewWidth: Int, isGrayScale: Boolean, isBlur: Boolean, imageBinder: ImageBinder): DetailAction.Render {
        val ratio           = intentData.imageOriginalWidth.toFloat() / intentData.imageOriginalHeight.toFloat()
        val requestedHeight = (viewWidth/ratio).toInt()

        val url = when {
            isGrayScale -> intentData.clickedPictureUrl + ImageApi.GRAY_SCALE_SUFFIX
            isBlur      -> intentData.clickedPictureUrl + ImageApi.BLUR_SUFFIX
            else        -> intentData.clickedPictureUrl
        }

        return DetailAction.Render(DetailViewRenderAction.Render(
            DetailViewState(
                id              = intentData.clickedPictureId,
                url             = url,
                requestedWidth  = viewWidth,
                requestedHeight = requestedHeight,
                author          = intentData.author,
                isGrayScale     = isGrayScale,
                isBlur          = isBlur,
                setImage        = imageBinder
            )
        ))
    }

}