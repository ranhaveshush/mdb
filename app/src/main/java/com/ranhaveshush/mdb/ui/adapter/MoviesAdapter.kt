package com.ranhaveshush.mdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.MainNavDirections
import com.ranhaveshush.mdb.databinding.ItemMovieBinding
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.vo.MovieItem

class MoviesAdapter(
    private val posterLoader: MovieItemPosterLoader
) : PagedListAdapter<MovieItem, MovieItemViewHolder>(MovieItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieItemViewHolder(binding, posterLoader)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindTo(movie)
    }
}

class MovieItemViewHolder(
    private val binding: ItemMovieBinding,
    private val posterLoader: MovieItemPosterLoader
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.movieItemClickListener = MovieItemClickListener
    }

    fun bindTo(movieItem: MovieItem?) {
        if (movieItem != null) {
            posterLoader.load(movieItem, binding.imageViewMovieThumbnail)
        }

        binding.movieItem = movieItem
        binding.executePendingBindings()
    }
}

object MovieItemClickListener {
    fun onMovieItemClick(view: View, movieItem: MovieItem) {
        val directions =
            MainNavDirections.actionGlobalMovieDetailsFragment(movieItem.id, movieItem.title)
        view.findNavController().navigate(directions)
    }
}

object MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem == newItem
}
