package com.knowre.android.codilitytest.base

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.ViewStateType


internal interface StateRenderer <in VS : ViewStateType, in A : Action> {
    suspend fun render(state: VS, action: A)
}