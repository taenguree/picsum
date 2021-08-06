package com.knowre.android.codilitytest.screen.main.dto

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.ViewCallbackAction
import com.knowre.android.codilitytest.knowRedux.ViewRenderAction
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewCallbackAction
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewRenderAction


internal sealed class MainAction : Action {
    data class Render(override val action: MainViewRenderAction) : MainAction(), ViewRenderAction<MainViewRenderAction>

    data class Callback(override val action: MainViewCallbackAction) : MainAction(), ViewCallbackAction<MainViewCallbackAction>
}