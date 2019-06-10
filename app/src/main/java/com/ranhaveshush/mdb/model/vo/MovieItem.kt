package com.ranhaveshush.mdb.model.vo

/**
 * The movie item [value object][ValueObject].
 */
data class MovieItem(
    val id: Int,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
    val posterUrl: String?
) : ValueObject
