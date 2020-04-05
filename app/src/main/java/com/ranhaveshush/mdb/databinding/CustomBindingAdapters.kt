package com.ranhaveshush.mdb.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.vo.MovieItem

@BindingAdapter("isGone")
fun View.isGone(gone: Boolean) {
    this.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    if (url.isNotEmpty()) {
        this.load(url)
    }
}

@BindingAdapter("items")
fun RecyclerView.loadItems(items: LiveData<PagedList<MovieItem>>?) {
    items?.value?.let {
        val adapter = this.adapter

        if (adapter is MoviesAdapter) {
            adapter.submitList(it)
        }
    }
}
