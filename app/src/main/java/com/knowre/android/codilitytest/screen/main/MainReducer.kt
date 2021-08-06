package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.screen.main.dto.MainAction
import com.knowre.android.codilitytest.screen.main.dto.MainState


internal class MainReducer : Reducer<MainState, MainAction> {

    override fun reduce(state: MainState, action: MainAction): MainState {
        return state
    }

}