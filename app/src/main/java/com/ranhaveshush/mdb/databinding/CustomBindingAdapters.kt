package com.ranhaveshush.mdb.databinding

import android.net.Uri
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

@BindingAdapter("imageUri")
fun ImageView.loadImage(uri: Uri?) {
    uri?.let {
        this.load(uri)
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
