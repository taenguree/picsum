package com.knowre.android.codilitytest.widget.singlePicture

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.knowre.android.codilitytest.databinding.ViewSinglePictureBinding
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.WidgetView
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureCallbackAction
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureRenderAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal class SinglePictureView constructor(
    context: Context,
    attrs: AttributeSet? = null

) : ConstraintLayout(context, attrs), WidgetView<SinglePictureViewState, SinglePictureRenderAction, SinglePictureCallbackAction> {

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
                Glide.with(context)
                    .load(action.state.url)
                    .override(action.state.requestedWidth, action.state.requestedHeight)
                    .into(binding.ivPicture)

                binding.tvAuthor.text = state.author
            }
        }
    }

    override fun setListener(listener: ViewCallbackListener<SinglePictureCallbackAction>) {
        this.listener = listener
    }

}