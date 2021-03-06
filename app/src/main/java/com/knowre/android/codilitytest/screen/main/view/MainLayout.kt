package com.knowre.android.codilitytest.screen.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.knowre.android.codilitytest.databinding.ViewActivityMainBinding
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewCallbackAction
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewRenderAction
import com.knowre.android.codilitytest.screen.main.view.dto.MainViewState
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget


internal class MainLayout constructor(
    context: Context,
    attrs: AttributeSet? = null

) : FrameLayout(context, attrs), Widget<MainViewState, MainViewRenderAction, MainViewCallbackAction> {

    val binding = ViewActivityMainBinding.inflate(LayoutInflater.from(context), this, true)

    override fun render(state: MainViewState, action: MainViewRenderAction) {
    }

    override fun setListener(listener: ViewCallbackListener<MainViewCallbackAction>) {
    }

}