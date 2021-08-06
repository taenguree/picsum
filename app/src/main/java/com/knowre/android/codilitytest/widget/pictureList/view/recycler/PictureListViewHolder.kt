package com.knowre.android.codilitytest.widget.pictureList.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.widget.base.Widget
import com.knowre.android.codilitytest.widget.singlePicture.SinglePictureLayout
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureCallbackAction
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureRenderAction
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState


internal class PictureListViewHolder constructor(private val view: View, private val singlePictureView: SinglePictureLayout) : RecyclerView.ViewHolder(view), Widget<SinglePictureViewState, SinglePictureRenderAction, SinglePictureCallbackAction> by singlePictureView {

    companion object {
        fun newInstance(parent: ViewGroup): PictureListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_single_picture, parent, false)

            return PictureListViewHolder(view, view.findViewById(R.id.custom_single_picture))
        }
    }
}