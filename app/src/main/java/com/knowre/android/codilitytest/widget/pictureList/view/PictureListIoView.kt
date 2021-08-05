package com.knowre.android.codilitytest.widget.pictureList.view

import android.animation.Animator
import android.view.View
import android.widget.ProgressBar
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.io.view.IoViewApi
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewCallbackAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewRenderAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState


internal class PictureListIoView constructor(val progressBar: ProgressBar) : IoViewApi {

    override fun render(state: IoViewState, action: IoViewRenderAction) {
        when (action) {
            is IoViewRenderAction.ShowLoading -> progressBar
                .animate()
                .alpha(1F)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                        progressBar.visibility = View.VISIBLE
                        progressBar.alpha = 0F
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        progressBar.visibility = View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                })
                .start()

            is IoViewRenderAction.HideLoading -> progressBar
                .animate()
                .alpha(1F)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                        progressBar.visibility = View.VISIBLE
                        progressBar.alpha = 1F
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        progressBar.visibility = View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                })
                .start()

            else -> Unit
        }
    }

    override fun setListener(listener: ViewCallbackListener<IoViewCallbackAction>) = Unit

}