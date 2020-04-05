package com.ranhaveshush.mdb.vo

/**
 * The movie details [value object][ValueObject].
 */
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: MovieReleaseDate,
    val runtime: MovieRuntime,
    val status: String,
    val vote: MovieVote,
    val backdropPath: String?
) : ValueObject
