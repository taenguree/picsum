package com.knowre.android.codilitytest.screen.detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.knowre.android.codilitytest.databinding.ViewActivityDetailBinding
import com.knowre.android.codilitytest.extensions.doOnPostLayout
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewCallbackAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewRenderAction
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget


internal class DetailLayout constructor(
    context: Context,
    attrs: AttributeSet? = null

) : FrameLayout(context, attrs), Widget<DetailViewState, DetailViewRenderAction, DetailViewCallbackAction> {

    private val binding = ViewActivityDetailBinding.inflate(LayoutInflater.from(context), this, true)

    private var listener: ViewCallbackListener<DetailViewCallbackAction>? = null

    private var state: DetailViewState? = null

    init {
        initializeListener()

        doOnPostLayout {
            listener?.onAction(DetailViewCallbackAction.OnInitialSizeMeasured(width = width))
        }
    }

    override fun render(state: DetailViewState, action: DetailViewRenderAction) {
        this.state = state

        when (action) {
            is DetailViewRenderAction.Render -> {
                binding.bGrayScale.visibility = View.VISIBLE
                binding.bBlur.visibility      = View.VISIBLE

                action.state.setImage(
                    binding.ivPicture,
                    { this.state!!.id },
                    action.state.id,
                    action.state.url,
                    false,
                    action.state.requestedWidth,
                    action.state.requestedHeight,
                    {
                        when {
                            action.state.isGrayScale -> {
                                binding.bGrayScale.visibility          = View.INVISIBLE
                                binding.bBlur.visibility               = View.INVISIBLE
                                binding.pbProgress.visibility          = View.INVISIBLE
                                binding.pbGrayScaleProgress.visibility = View.VISIBLE
                            }

                            action.state.isBlur -> {
                                binding.bGrayScale.visibility     = View.INVISIBLE
                                binding.bBlur.visibility          = View.INVISIBLE
                                binding.pbProgress.visibility     = View.INVISIBLE
                                binding.pbBlurProgress.visibility = View.VISIBLE
                            }

                            else -> binding.pbProgress.visibility = View.VISIBLE
                        }
                    },
                    {
                        when {
                            action.state.isGrayScale -> {
                                binding.bGrayScale.visibility          = View.VISIBLE
                                binding.bBlur.visibility               = View.VISIBLE
                                binding.pbGrayScaleProgress.visibility = View.INVISIBLE
                            }

                            action.state.isBlur -> {
                                binding.bGrayScale.visibility     = View.VISIBLE
                                binding.bBlur.visibility          = View.VISIBLE
                                binding.pbBlurProgress.visibility = View.INVISIBLE
                            }

                            else -> binding.pbProgress.visibility = View.INVISIBLE
                        }
                    },
                )

                binding.tvAuthor.text = action.state.author
            }
        }
    }

    override fun setListener(listener: ViewCallbackListener<DetailViewCallbackAction>) {
        this.listener = listener
    }

    private fun initializeListener() {
        binding.bGrayScale.setOnClickListener {
            listener?.onAction(DetailViewCallbackAction.OnGrayScaleClicked(width))
        }

        binding.bBlur.setOnClickListener {
            listener?.onAction(DetailViewCallbackAction.OnBlurClicked(width))
        }
    }

}