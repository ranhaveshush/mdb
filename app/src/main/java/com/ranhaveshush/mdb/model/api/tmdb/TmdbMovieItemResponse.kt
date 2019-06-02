package com.ranhaveshush.mdb.model.api.tmdb

import com.ranhaveshush.mdb.model.api.MovieItemResponse
import com.squareup.moshi.Json

/**
 * A TMDb [movie item][MovieItemResponse] in a
 * [movies page response][TmdbMoviesPageResponse]'s results property.
 */
data class TmdbMovieItemResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val posterPath: String
) : MovieItemResponse
