package com.ranhaveshush.mdb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.model.vo.MovieItem
import com.ranhaveshush.mdb.view.MoviesListFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private var movies: List<MovieItem>) : RecyclerView.Adapter<MovieItemViewHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        itemView.setOnClickListener {
            val movieId = it.getTag(R.id.tag_movie_id) as Int
            val movieTitle = it.getTag(R.id.tag_movie_title) as String
            val action = MoviesListFragmentDirections.presentMovieDetailsAction(movieId, movieTitle)
            val onClickListener = Navigation.createNavigateOnClickListener(action)

            onClickListener.onClick(it)
        }

        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) = holder.bind(movies[position])

    // TODO: 5/30/19 improve the performance with DiffUtil.
    fun load(data: List<MovieItem>?) {
        if (data != null) {
            movies = data
            notifyDataSetChanged()
        }
    }
}

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieTitle = itemView.textView_movieTitle

    fun bind(movie: MovieItem) {
        itemView.setTag(R.id.tag_movie_id, movie.id)
        itemView.setTag(R.id.tag_movie_title, movie.title)

        movieTitle.text = movie.title
    }
}
