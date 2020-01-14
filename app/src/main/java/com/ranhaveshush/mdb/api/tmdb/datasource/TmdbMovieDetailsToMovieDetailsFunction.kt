package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.arch.core.util.Function
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieDetails
import com.ranhaveshush.mdb.vo.MovieDetails

/**
 * This [Function] converts [TmdbMovieDetails] data object to [MovieDetails] value object
 * consumed by the UI layer.
 */
object TmdbMovieDetailsToMovieDetailsFunction : Function<TmdbMovieDetails, MovieDetails> {
    override fun apply(input: TmdbMovieDetails): MovieDetails = MovieDetails(
        input.id,
        input.title,
        input.overview,
        input.releaseDate,
        input.runtime,
        input.status,
        input.voteAverage,
        input.voteCount,
        input.backdropPath
    )
}
