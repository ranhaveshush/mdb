package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.arch.core.util.Function
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieItem
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * This [Function] converts [TmdbMovieItem] data object to [MovieItem] value object
 * consumed by the UI layer.
 */
class TmdbMovieItemToMovieItemFunction : Function<TmdbMovieItem, MovieItem> {
    override fun apply(input: TmdbMovieItem): MovieItem = MovieItem(
        input.id,
        input.title,
        input.voteAverage,
        input.voteCount,
        input.posterPath
    )
}
