package com.knowre.android.codilitytest.screen.detail

import com.knowre.android.codilitytest.base.ActionEmitter
import com.knowre.android.codilitytest.base.ActionListener
import com.knowre.android.codilitytest.base.StateRenderer
import com.knowre.android.codilitytest.screen.detail.dto.DetailAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget


internal class DetailRenderer constructor(private val detailLayout: Widget<DetailViewState, DetailViewRenderAction, DetailViewCallbackAction>) : StateRenderer<DetailViewState, DetailAction>, ActionEmitter<DetailAction> {

    private var listener: ActionListener<DetailAction>? = null

    init {
        detailLayout.setListener(object : ViewCallbackListener<DetailViewCallbackAction> {
            override fun onAction(action: DetailViewCallbackAction) {
                listener?.onAction(DetailAction.Callback(action))
            }
        })
    }

    override suspend fun render(state: DetailViewState, action: DetailAction) {
        when (action) {
            is DetailAction.Render -> detailLayout.render(state, action.action)

            else -> Unit
        }
    }

    override fun setListener(listener: ActionListener<DetailAction>) {
        this.listener = listener
    }

}