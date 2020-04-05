package com.ranhaveshush.mdb.api.tmdb.datasource

import android.net.Uri
import com.ranhaveshush.mdb.api.Function
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieItem
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.MoviePoster

/**
 * This [Function] converts [TmdbMovieItem] data object to [MovieItem] value object
 * consumed by the UI layer.
 */
class TmdbMovieItemToMovieItemFunction : Function<TmdbMovieItem, MovieItem> {
    override fun apply(input: TmdbMovieItem): MovieItem {
        val posterUri = Uri.parse(input.posterUrl)

        return MovieItem(
            input.id,
            input.title,
            MoviePoster(posterUri)
        )
    }
}
