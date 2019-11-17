package com.ranhaveshush.mdb.vo

/**
 * The movie details [value object][ValueObject].
 */
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int?,
    val status: String,
    val voteAverage: Float,
    val voteCount: Int,
    val backdropPath: String
) : ValueObject
