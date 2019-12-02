package com.ranhaveshush.mdb.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * The margin grid [RecyclerView.ItemDecoration] implementation.
 *
 * Equally sets the recycler view item's margin from all directions.
 */
class MarginGridItemDecoration(
    private val itemWidth: Int,
    private val screenWidth: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager
        if (layoutManager !is GridLayoutManager) {
            throw IllegalStateException("Expecting a GridLayoutManager, got $layoutManager")
        }

        val spanCount = layoutManager.spanCount
        val totalOffset = screenWidth - (spanCount * itemWidth)
        val offsetsCount = spanCount * 2
        val itemOffset = totalOffset / offsetsCount

        outRect.top = itemOffset
        outRect.left = itemOffset
        outRect.right = itemOffset
        outRect.bottom = itemOffset
    }
}
