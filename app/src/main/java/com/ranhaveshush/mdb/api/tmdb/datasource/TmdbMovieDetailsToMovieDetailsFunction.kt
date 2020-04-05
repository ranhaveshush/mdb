package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.arch.core.util.Function
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieDetails
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieReleaseDate
import com.ranhaveshush.mdb.vo.MovieRuntime
import com.ranhaveshush.mdb.vo.MovieVote
import java.util.Calendar

/**
 * This [Function] converts [TmdbMovieDetails] data object to [MovieDetails] value object
 * consumed by the UI layer.
 */
 class TmdbMovieDetailsToMovieDetailsFunction : Function<TmdbMovieDetails, MovieDetails> {
    override fun apply(input: TmdbMovieDetails): MovieDetails {
        val releaseDateSegments = input.releaseDate.split("-")
        val year: Int = releaseDateSegments[0].toInt()
        val month: Int = releaseDateSegments[1].toInt()
        val date: Int = releaseDateSegments[2].toInt()

        val releaseDate: Calendar = Calendar.getInstance()
        releaseDate.set(year, month, date)

        return MovieDetails(
            input.id,
            input.title,
            input.overview,
            MovieReleaseDate(releaseDate),
            MovieRuntime.create(input.runtime),
            input.status,
            MovieVote(input.voteAverage, input.voteCount),
            input.backdropPath
        )
    }
}
