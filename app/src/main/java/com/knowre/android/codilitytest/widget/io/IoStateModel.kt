package com.knowre.android.codilitytest.widget.io

import com.knowre.android.codilitytest.base.LifecycleAwareStateModel
import com.knowre.android.codilitytest.http.callState.CallStateListenerApi
import com.knowre.android.codilitytest.knowRedux.LoggingMiddleware
import com.knowre.android.codilitytest.widget.io.dto.IoAction
import com.knowre.android.codilitytest.widget.io.dto.IoState
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewRenderAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import javax.inject.Inject


internal class IoStateModel @Inject constructor() : LifecycleAwareStateModel<IoViewState, IoState, IoAction>(
    initialState = IoState(),
    reducer      = IoReducer(),
    middleware   = LoggingMiddleware("NET")
), CallStateListenerApi<Call<*>> {

    override fun onInitialCallStarted(call: Call<*>) {
        launch(Dispatchers.Main.immediate) {
            dispatch(IoAction.Render(IoViewRenderAction.ShowLoading()))
        }
    }

    override fun onRetryingBackoffStarted(call: Call<*>, throwable: Throwable, backOffDelay: Long, retryCount: Int) {
    }

    override fun onRetryCallStarted(call: Call<*>, throwable: Throwable, retryCount: Int) {
    }

    override fun onSucceed(call: Call<*>) {
        launch(Dispatchers.Main.immediate) {
            dispatch(IoAction.Render(IoViewRenderAction.HideLoading()))
        }
    }

    override fun onFailure(call: Call<*>, throwable: Throwable, retryCount: Int) {
        launch(Dispatchers.Main.immediate) {
            dispatch(IoAction.Render(IoViewRenderAction.HideLoading()))
        }
    }

    override fun onResponseIgnored(call: Call<*>) {
        launch(Dispatchers.Main.immediate) {
            dispatch(IoAction.Render(IoViewRenderAction.HideLoading()))
        }
    }

}