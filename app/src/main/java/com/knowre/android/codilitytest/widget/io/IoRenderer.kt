package com.knowre.android.codilitytest.widget.io

import com.knowre.android.codilitytest.base.ActionEmitter
import com.knowre.android.codilitytest.base.ActionListener
import com.knowre.android.codilitytest.base.StateRenderer
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.io.dto.IoAction
import com.knowre.android.codilitytest.widget.io.view.IoViewApi
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewCallbackAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState


internal class IoRenderer constructor(private val ioView: IoViewApi) : StateRenderer<IoViewState, IoAction>, ActionEmitter<IoAction> {

    private var listener: ActionListener<IoAction>? = null

    init {
        ioView.setListener(object : ViewCallbackListener<IoViewCallbackAction> {
            override fun onAction(action: IoViewCallbackAction) {
                listener?.onAction(IoAction.Callback(action))
            }
        })
    }

    override suspend fun render(state: IoViewState, action: IoAction) {
        when (action) {
            is IoAction.Render -> ioView.render(state, action.action)

            else -> Unit
        }
    }

    override fun setListener(listener: ActionListener<IoAction>) {
        this.listener = listener
    }

}