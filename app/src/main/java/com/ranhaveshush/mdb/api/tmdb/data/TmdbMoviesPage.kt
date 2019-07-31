package com.ranhaveshush.mdb.api.tmdb.data

import com.squareup.moshi.Json

/**
 * A TMDb movies page response object.
 */
data class TmdbMoviesPage(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "total_results") val totalResults: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "results") val results: List<TmdbMovieItem>
)
