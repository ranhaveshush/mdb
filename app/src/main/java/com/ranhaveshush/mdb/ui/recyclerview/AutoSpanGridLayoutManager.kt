package com.ranhaveshush.mdb.ui.recyclerview

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

const val INITIAL_SPAN_COUNT = 1

/**
 * The auto span [GridLayoutManager] implementation.
 *
 * Automatically calculates the [GridLayoutManager] span count for a given [columnWidth].
 */
class AutoSpanGridLayoutManager(
    context: Context?,
    private val columnWidth: Int
) : GridLayoutManager(context, INITIAL_SPAN_COUNT) {
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (width > 0 && height > 0 && columnWidth > 0) {
            val totalSpace = when (orientation) {
                VERTICAL -> width - paddingStart - paddingEnd
                HORIZONTAL -> height - paddingTop - paddingBottom
                else -> throw IllegalStateException("Unsupported LayoutManager orientation $orientation.")
            }

            spanCount = max(1, totalSpace / columnWidth)
        }

        super.onLayoutChildren(recycler, state)
    }
}
