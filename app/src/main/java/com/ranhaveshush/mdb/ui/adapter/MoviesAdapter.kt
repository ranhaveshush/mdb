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
import com.ranhaveshush.mdb.vo.MovieItem

class MoviesAdapter : PagedListAdapter<MovieItem, MovieItemViewHolder>(MovieItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindTo(movie)
    }
}

class MovieItemViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.movieItemClickListener = MovieItemClickListener()
    }

    fun bindTo(movieItem: MovieItem?) {
        binding.movieItem = movieItem
        binding.executePendingBindings()
    }
}

class MovieItemClickListener {
    fun onMovieItemClick(view: View, movieItem: MovieItem) {
        val action = MainNavDirections.actionGlobalMovieDetailsFragment(movieItem.id, movieItem.title)
        view.findNavController().navigate(action)
    }
}

object MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem == newItem
}
