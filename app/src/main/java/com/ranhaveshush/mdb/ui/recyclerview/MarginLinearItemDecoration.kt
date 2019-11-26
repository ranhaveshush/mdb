package com.ranhaveshush.mdb.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * The margin linear [RecyclerView.ItemDecoration] implementation.
 *
 * Equally sets the recycler view item's margin from all directions.
 */
class MarginLinearItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager
        if (layoutManager !is LinearLayoutManager) {
            throw IllegalStateException("Expecting a LinearLayoutManager, got $layoutManager")
        }

        val position = parent.getChildAdapterPosition(view)

        when (layoutManager.orientation) {
            LinearLayoutManager.VERTICAL -> {
                if (position == 0) {
                    outRect.top = margin
                }
                outRect.left = margin
                outRect.right = margin
                outRect.bottom = margin
            }
            LinearLayoutManager.HORIZONTAL -> {
                if (position == 0) {
                    outRect.left = margin
                }
                outRect.right = margin
                outRect.top = margin
                outRect.bottom = margin
            }
        }
    }
}
