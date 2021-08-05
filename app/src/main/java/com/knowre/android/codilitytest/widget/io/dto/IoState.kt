package com.knowre.android.codilitytest.widget.io.dto

import com.knowre.android.codilitytest.knowRedux.StateType
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState


internal data class IoState(
    override val viewState: IoViewState = IoViewState(),
    val InProgressCallList: List<String> = listOf()

) : StateType, ViewStateAware<IoViewState>