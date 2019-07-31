package com.ranhaveshush.mdb.api.tmdb.data

import com.squareup.moshi.Json

/**
 * A TMDb movie item in a [TMDb movies page][TmdbMoviesPage]'s results property.
 */
data class TmdbMovieItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val posterPath: String
)
