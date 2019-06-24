package com.ranhaveshush.mdb.api.tmdb.response

import com.squareup.moshi.Json

/**
 * A TMDb movie item in a [movies page response][TmdbMoviesPageResponse]'s results property.
 */
data class TmdbMovieItemResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val posterPath: String
)