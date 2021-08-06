package com.knowre.android.codilitytest.widget.io.view

import android.animation.Animator
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.io.view.IoViewApi
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewCallbackAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewRenderAction
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState


internal class BasicIoView constructor(val progressBar: ProgressBar, val context: Context) : IoViewApi {

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

                    override fun onAnimationEnd(animation: Animator?) = Unit

                    override fun onAnimationCancel(animation: Animator?) = Unit

                    override fun onAnimationRepeat(animation: Animator?) = Unit
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

                    override fun onAnimationCancel(animation: Animator?) = Unit

                    override fun onAnimationRepeat(animation: Animator?) = Unit
                })
                .start()

            is IoViewRenderAction.ShowRetryMessage -> Toast.makeText(context, context.getString(R.string.retry_due_to_network_unstable), Toast.LENGTH_SHORT).show()

            is IoViewRenderAction.ShowIoFailMessage -> Toast.makeText(context, context.getString(R.string.network_not_available), Toast.LENGTH_SHORT).show()
        }
    }

    override fun setListener(listener: ViewCallbackListener<IoViewCallbackAction>) = Unit

}