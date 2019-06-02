package com.ranhaveshush.mdb.model.vo

/**
 * The movies result page [value object][ValueObject].
 */
data class MoviesPage(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<MovieItem>
) : ValueObject
