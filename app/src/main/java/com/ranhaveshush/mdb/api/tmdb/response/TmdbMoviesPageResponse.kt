package com.ranhaveshush.mdb.api.tmdb.response

import com.squareup.moshi.Json

/**
 * A TMDb movies page response object.
 */
data class TmdbMoviesPageResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "total_results") val totalResults: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "results") val results: List<TmdbMovieItemResponse>
)
