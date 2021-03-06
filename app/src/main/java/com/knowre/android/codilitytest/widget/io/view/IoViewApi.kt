package com.knowre.android.codilitytest.widget.io.view

import com.knowre.android.codilitytest.widget.base.Widget
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewCallbackAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewRenderAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState


internal interface IoViewApi : Widget<IoViewState, IoViewRenderAction, IoViewCallbackAction>