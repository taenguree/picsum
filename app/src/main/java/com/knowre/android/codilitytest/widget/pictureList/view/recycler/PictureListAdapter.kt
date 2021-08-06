package com.knowre.android.codilitytest.widget.pictureList.view.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knowre.android.codilitytest.widget.base.ViewCallbackActionEmitter
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureCallbackAction
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureRenderAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal class PictureListAdapter : RecyclerView.Adapter<PictureListViewHolder>(), ViewCallbackActionEmitter<SinglePictureCallbackAction> {

    private val states = mutableListOf<SinglePictureViewState>()

    private var listener: ViewCallbackListener<SinglePictureCallbackAction>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        return PictureListViewHolder.newInstance(parent).apply {
            setListener(object : ViewCallbackListener<SinglePictureCallbackAction> {
                override fun onAction(action: SinglePictureCallbackAction) {
                    listener?.onAction(action)
                }
            })
        }
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        val state = states[position]

        holder.render(state, SinglePictureRenderAction.Render(state))
    }

    override fun getItemCount() = states.size

    override fun setListener(listener: ViewCallbackListener<SinglePictureCallbackAction>) {
        this.listener = listener
    }

    fun addStates(states: List<SinglePictureViewState>) {
        val insertPosition = this.states.size

        this.states.addAll(states)

        if (insertPosition == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(insertPosition)
        }
    }

}