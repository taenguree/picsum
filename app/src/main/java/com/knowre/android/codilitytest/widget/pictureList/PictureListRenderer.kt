package com.knowre.android.codilitytest.widget.pictureList

import com.knowre.android.codilitytest.base.ActionEmitter
import com.knowre.android.codilitytest.base.ActionListener
import com.knowre.android.codilitytest.base.StateRenderer
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState


internal class PictureListRenderer constructor(private val widget: Widget<PictureListViewState, PictureListRenderAction, PictureListCallbackAction>) : StateRenderer<PictureListViewState, PictureListAction>, ActionEmitter<PictureListAction> {

    private var listener: ActionListener<PictureListAction>? = null

    init {
        widget.setListener(object : ViewCallbackListener<PictureListCallbackAction> {
            override fun onAction(action: PictureListCallbackAction) {
                listener?.onAction(PictureListAction.Callback(action))
            }
        })
    }

    override suspend fun render(state: PictureListViewState, action: PictureListAction) {
        when (action) {
            is PictureListAction.Render -> widget.render(state, action.action)

            else -> Unit
        }
    }

    override fun setListener(listener: ActionListener<PictureListAction>) {
        this.listener = listener
    }

}