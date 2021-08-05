package com.knowre.android.codilitytest.widget.pictureList.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.databinding.ViewPictureListBinding
import com.knowre.android.codilitytest.extensions.doOnPostLayout
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.WidgetView
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.recycler.PictureListAdapter
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState


internal class PictureListView constructor(
    context: Context,
    attrs: AttributeSet? = null

) : ConstraintLayout(context, attrs), WidgetView<PictureListViewState, PictureListRenderAction, PictureListCallbackAction> {

    companion object {
        const val COLUMN_COUNT = 2
    }

    val binding = ViewPictureListBinding.inflate(LayoutInflater.from(context), this, true)

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
            is PictureListRenderAction.AppendPictures  -> pictureListAdapter.addStates(action.singlePictureStates)
            is PictureListRenderAction.ShowAppendToast -> if (action.isNoMorePictureExists) { resources.getString(R.string.no_more_picture_exists) } else { resources.getString(R.string.load_more_picture_success) }
                .also { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun setListener(listener: ViewCallbackListener<PictureListCallbackAction>) {
        this.listener = listener
    }

    private fun initializeRecyclerView() {
        with(binding.rvPictures) {
            layoutManager = StaggeredGridLayoutManager(COLUMN_COUNT, RecyclerView.VERTICAL)
            adapter       = pictureListAdapter
        }

        binding.rvPictures.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val lastItemPosition = (recyclerView.layoutManager as StaggeredGridLayoutManager).findLastCompletelyVisibleItemPositions(null)[0]

                    val itemCount = recyclerView.adapter!!.itemCount

                    if (lastItemPosition > itemCount - 10) {
                        listener?.onAction(PictureListCallbackAction.OnAlmostScrolledToVeryBottom(itemCount))
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

}