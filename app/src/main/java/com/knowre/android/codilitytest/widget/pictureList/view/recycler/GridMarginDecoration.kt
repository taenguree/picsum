package com.knowre.android.codilitytest.widget.pictureList.view.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


internal class GridMarginDecoration constructor(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val spanIndex: Int = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex

        if (spanIndex == 0) {
            outRect.left = spacing
            outRect.right = spacing / spanCount
        } else {
            outRect.left = spacing / spanCount
            outRect.right = spacing
        }

        if (position < spanCount) {
            outRect.top = spacing
        }

        outRect.bottom = spacing
    }

}