package com.knowre.android.codilitytest.widget.pictureList.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.knowre.android.codilitytest.databinding.ViewPictureListBinding
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.recycler.PictureListAdapter
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState


internal class PictureListView constructor(
    context: Context,
    attrs: AttributeSet? = null

) : ConstraintLayout(context, attrs) {

    private val binding = ViewPictureListBinding.inflate(LayoutInflater.from(context), this, true)

    private var pictureListAdapter = PictureListAdapter()

    init { initializeRecyclerView() }

    fun render(state: PictureListViewState, action: PictureListRenderAction) {
        when (action) {
            is PictureListRenderAction.AppendPictures -> post { pictureListAdapter.addStates(action.singlePictureStates) }
        }
    }

    private fun initializeRecyclerView() {
        with(binding.rvPictures) {
            layoutManager = GridLayoutManager(context, 2)
            adapter       = pictureListAdapter
        }
    }

}