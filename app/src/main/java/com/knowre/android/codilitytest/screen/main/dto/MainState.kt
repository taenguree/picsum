package com.knowre.android.codilitytest.screen.main.dto

import com.knowre.android.codilitytest.knowRedux.StateType
import com.knowre.android.codilitytest.knowRedux.ViewStateAware
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewState


internal class MainState(override val viewState: MainViewState = MainViewState()) : StateType, ViewStateAware<MainViewState>