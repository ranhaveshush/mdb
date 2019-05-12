package com.ranhaveshush.tmdb.model.dto

import com.squareup.moshi.Json

/**
 * The movie details object.
 */
data class MovieDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Float,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "status") val status: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String
)