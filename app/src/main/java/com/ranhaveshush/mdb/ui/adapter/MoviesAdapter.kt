package com.ranhaveshush.mdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.vo.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    private val movies: List<MovieItem>,
    private val onMovieItemClickListener: (movieId: Int, movieTitle: String) -> Unit
) : RecyclerView.Adapter<MovieItemViewHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        itemView.setOnClickListener {
            val movieId = it.getTag(R.id.tag_movie_id) as Int
            val movieTitle = it.getTag(R.id.tag_movie_title) as String

            onMovieItemClickListener(movieId, movieTitle)
        }

        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) = holder.bind(movies[position])
}

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieTitle = itemView.textView_movieTitle

    fun bind(movie: MovieItem) {
        itemView.setTag(R.id.tag_movie_id, movie.id)
        itemView.setTag(R.id.tag_movie_title, movie.title)

        movieTitle.text = movie.title
    }
}
