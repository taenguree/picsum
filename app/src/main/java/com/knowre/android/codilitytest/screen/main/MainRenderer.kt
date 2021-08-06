package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.ActionEmitter
import com.knowre.android.codilitytest.base.ActionListener
import com.knowre.android.codilitytest.base.StateRenderer
import com.knowre.android.codilitytest.screen.main.dto.MainAction
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewState


internal class MainRenderer : StateRenderer<MainViewState, MainAction>, ActionEmitter<MainAction> {

    private var listener: ActionListener<MainAction>? = null

    override suspend fun render(state: MainViewState, action: MainAction) {}

    override fun setListener(listener: ActionListener<MainAction>) {
        this.listener = listener
    }

}