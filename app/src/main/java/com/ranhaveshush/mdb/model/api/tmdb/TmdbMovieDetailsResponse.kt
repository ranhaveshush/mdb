package com.ranhaveshush.mdb.model.api.tmdb

import com.ranhaveshush.mdb.model.api.MovieDetailsResponse
import com.squareup.moshi.Json

/**
 * A TMDb [movie details response][MovieDetailsResponse] object.
 */
data class TmdbMovieDetailsResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "runtime") val runtime: Int,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val posterPath: String
) : MovieDetailsResponse
