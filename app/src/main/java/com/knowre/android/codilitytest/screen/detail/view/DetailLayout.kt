package com.knowre.android.codilitytest.screen.detail.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget


internal class DetailLayout constructor(
    context: Context,
    attrs: AttributeSet? = null

) : FrameLayout(context, attrs), Widget<DetailViewState, DetailViewRenderAction, DetailViewCallbackAction> {

    private var listener: ViewCallbackListener<DetailViewCallbackAction>? = null

    override fun render(state: DetailViewState, action: DetailViewRenderAction) {
    }

    override fun setListener(listener: ViewCallbackListener<DetailViewCallbackAction>) {
        this.listener = listener
    }

}