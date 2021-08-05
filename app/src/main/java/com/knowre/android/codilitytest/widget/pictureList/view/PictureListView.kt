package com.knowre.android.codilitytest.widget.pictureList.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.knowre.android.codilitytest.databinding.ViewPictureListBinding
import com.knowre.android.codilitytest.extensions.doOnPostLayout
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.WidgetView
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.recycler.PictureListAdapter
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState


internal class PictureListView constructor(
    context: Context,
    attrs: AttributeSet? = null

) : ConstraintLayout(context, attrs), WidgetView<PictureListViewState, PictureListRenderAction, PictureListCallbackAction> {

    private val binding = ViewPictureListBinding.inflate(LayoutInflater.from(context), this, true)

    private var pictureListAdapter = PictureListAdapter()

    private var listener: ViewCallbackListener<PictureListCallbackAction>? = null

    init {
        initializeRecyclerView()

        doOnPostLayout {
            listener?.onAction(PictureListCallbackAction.OnInitialSizeMeasured(it.width, it.height, horizontalMarginsSum = 0, verticalMarginsSum = 0))
        }
    }

    override fun render(state: PictureListViewState, action: PictureListRenderAction) {
        when (action) {
            is PictureListRenderAction.AppendPictures -> post { pictureListAdapter.addStates(action.singlePictureStates) }
        }
    }

    override fun setListener(listener: ViewCallbackListener<PictureListCallbackAction>) {
        this.listener = listener
    }

    private fun initializeRecyclerView() {
        with(binding.rvPictures) {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter       = pictureListAdapter
        }
    }

}