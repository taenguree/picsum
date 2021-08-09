package com.knowre.android.codilitytest.widget.singlePicture

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.knowre.android.codilitytest.databinding.ViewSinglePictureBinding
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureCallbackAction
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureRenderAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal class SinglePictureLayout constructor(
    context: Context,
    attrs: AttributeSet? = null

) : FrameLayout(context, attrs), Widget<SinglePictureViewState, SinglePictureRenderAction, SinglePictureCallbackAction> {

    private val binding = ViewSinglePictureBinding.inflate(LayoutInflater.from(context), this, true)

    private var state: SinglePictureViewState? = null

    private var listener: ViewCallbackListener<SinglePictureCallbackAction>? = null

    init {
        setOnClickListener { state?.let { listener?.onAction(SinglePictureCallbackAction.Clicked(it)) } }
    }

    override fun render(state: SinglePictureViewState, action: SinglePictureRenderAction) {
        this.state = state

        when (action) {
            is SinglePictureRenderAction.Render -> {
                state.setImage(
                    binding.ivPicture,
                    { this.state!!.id },
                    state.id,
                    state.url,
                    true,
                    state.requestedWidth,
                    state.requestedHeight,
                    { binding.pbProgress.visibility = View.VISIBLE },
                    { binding.pbProgress.visibility = View.GONE },
                )

                binding.tvAuthor.text = state.author
            }
        }
    }

    override fun setListener(listener: ViewCallbackListener<SinglePictureCallbackAction>) {
        this.listener = listener
    }

}