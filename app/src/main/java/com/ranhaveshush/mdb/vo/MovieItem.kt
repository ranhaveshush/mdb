package com.ranhaveshush.mdb.vo

/**
 * The movie item [value object][ValueObject].
 */
data class MovieItem(
    val id: Int,
    val title: String,
    val poster: MoviePoster
) : ValueObject
