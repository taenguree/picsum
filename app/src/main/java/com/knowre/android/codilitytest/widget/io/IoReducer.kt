package com.knowre.android.codilitytest.widget.io

import com.knowre.android.codilitytest.knowRedux.Reducer
import com.knowre.android.codilitytest.widget.io.dto.IoAction
import com.knowre.android.codilitytest.widget.io.dto.IoState


internal class IoReducer : Reducer<IoState, IoAction> {

    override fun reduce(state: IoState, action: IoAction): IoState {
        return state
    }

}