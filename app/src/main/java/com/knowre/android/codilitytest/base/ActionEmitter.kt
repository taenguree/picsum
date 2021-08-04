package com.knowre.android.codilitytest.base

import com.knowre.android.codilitytest.knowRedux.Action


internal interface ActionEmitter<A : Action> {
    fun setListener(listener: ActionListener<A>)
}