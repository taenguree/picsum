package com.knowre.android.codilitytest.widget.pictureList.view.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureRenderAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal class PictureListAdapter : RecyclerView.Adapter<PictureListViewHolder>() {

    private val states = mutableListOf<SinglePictureViewState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        return PictureListViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        val state = states[position]

        holder.render(state, SinglePictureRenderAction.Render(state))
    }

    override fun getItemCount() = states.size

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