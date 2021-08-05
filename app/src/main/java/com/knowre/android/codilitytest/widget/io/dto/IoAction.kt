package com.knowre.android.codilitytest.widget.io.dto

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.ViewCallbackAction
import com.knowre.android.codilitytest.knowRedux.ViewRenderAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewCallbackAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewRenderAction


internal sealed class IoAction : Action {
    data class Render(override val action: IoViewRenderAction) : IoAction(), ViewRenderAction<IoViewRenderAction>

    data class Callback(override val action: IoViewCallbackAction) : IoAction(), ViewCallbackAction<IoViewCallbackAction>
}