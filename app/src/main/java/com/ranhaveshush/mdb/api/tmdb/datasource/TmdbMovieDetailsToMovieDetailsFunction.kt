package com.ranhaveshush.mdb.api.tmdb.datasource

import android.net.Uri
import com.ranhaveshush.mdb.api.Function
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieDetails
import com.ranhaveshush.mdb.vo.MovieBackdrop
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

        val backdropUri = Uri.parse(input.backdropUrl)

        return MovieDetails(
            input.id,
            input.title,
            input.overview,
            input.status,
            MovieReleaseDate(releaseDate),
            MovieRuntime.create(input.runtime),
            MovieVote(input.voteAverage, input.voteCount),
            MovieBackdrop(backdropUri)
        )
    }
}
