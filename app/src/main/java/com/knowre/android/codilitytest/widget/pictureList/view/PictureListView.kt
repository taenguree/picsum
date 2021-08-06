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
import com.knowre.android.codilitytest.extensions.toPx
import com.knowre.android.codilitytest.widget.base.ViewCallbackListener
import com.knowre.android.codilitytest.widget.base.Widget
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.recycler.PictureListAdapter
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState
import com.knowre.android.codilitytest.widget.pictureList.view.recycler.GridMarginDecoration
import com.knowre.android.codilitytest.widget.singlePicture.action.SinglePictureCallbackAction


internal class PictureListView constructor(
    context: Context,
    attrs: AttributeSet? = null

) : ConstraintLayout(context, attrs), Widget<PictureListViewState, PictureListRenderAction, PictureListCallbackAction> {

    companion object {
        private const val VERY_BOTTOM_SCROLL_ITEM_THRESHOLD = 15

        const val COLUMN_COUNT = 2

        val GRID_MARGIN_IN_PX = 4.toPx
    }

    val binding = ViewPictureListBinding.inflate(LayoutInflater.from(context), this, true)

    private var pictureListAdapter = PictureListAdapter()

    private var listener: ViewCallbackListener<PictureListCallbackAction>? = null

    init {
        initializeRecyclerView()
        initializeListener()

        doOnPostLayout {
            listener?.onAction(PictureListCallbackAction.OnInitialSizeMeasured(it.width, it.height, horizontalMarginsSum = GRID_MARGIN_IN_PX * (COLUMN_COUNT + 1), verticalMarginsSum = 0))
        }
    }

    override fun render(state: PictureListViewState, action: PictureListRenderAction) {
        when (action) {
            is PictureListRenderAction.AppendPictures            -> pictureListAdapter.addStates(action.singlePictureStates)
            is PictureListRenderAction.ShowMorePictureExistToast -> Toast.makeText(context, resources.getString(R.string.no_more_picture_exists), Toast.LENGTH_SHORT).show()
        }
    }

    override fun setListener(listener: ViewCallbackListener<PictureListCallbackAction>) {
        this.listener = listener
    }

    private fun initializeRecyclerView() {
        with(binding.rvPictures) {
            layoutManager = StaggeredGridLayoutManager(COLUMN_COUNT, RecyclerView.VERTICAL)
            adapter       = pictureListAdapter
            addItemDecoration(GridMarginDecoration(2, GRID_MARGIN_IN_PX))
        }

        binding.rvPictures.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val lastItemPosition = (recyclerView.layoutManager as StaggeredGridLayoutManager).findLastCompletelyVisibleItemPositions(null)[0]

                    val itemCount = recyclerView.adapter!!.itemCount

                    if (lastItemPosition > itemCount - VERY_BOTTOM_SCROLL_ITEM_THRESHOLD) {
                        listener?.onAction(PictureListCallbackAction.OnAlmostScrolledToVeryBottom())
                    }
                }
            }
        })
    }

    private fun initializeListener() {
        pictureListAdapter.setListener(object : ViewCallbackListener<SinglePictureCallbackAction> {
            override fun onAction(action: SinglePictureCallbackAction) {
                when (action) {
                    is SinglePictureCallbackAction.Clicked -> listener?.onAction(PictureListCallbackAction.OnImageClicked(action.state))
                }
            }
        })
    }

}