package com.knowre.android.codilitytest.base

import com.knowre.android.codilitytest.knowRedux.Action


internal interface ActionListener<in A: Action> {
    fun onAction(action: A)
}