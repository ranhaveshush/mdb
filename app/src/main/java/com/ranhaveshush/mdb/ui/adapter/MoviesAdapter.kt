package com.ranhaveshush.mdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.MainNavDirections
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.vo.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : PagedListAdapter<MovieItem, MovieItemViewHolder>(MovieItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        itemView.setOnClickListener {
            val movieId = it.getTag(R.id.tag_movie_id) as Int
            val movieTitle = it.getTag(R.id.tag_movie_title) as String

            val action = MainNavDirections.actionGlobalMovieDetailsFragment(movieId, movieTitle)
            it.findNavController().navigate(action)
        }

        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindTo(movie)
    }
}

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieTitle = itemView.textView_movieTitle

    fun bindTo(movie: MovieItem?) {
        itemView.setTag(R.id.tag_movie_id, movie?.id)
        itemView.setTag(R.id.tag_movie_title, movie?.title)

        movieTitle.text = movie?.title
    }
}

object MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem == newItem
}
