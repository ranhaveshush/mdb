package com.ranhaveshush.mdb.model.api.tmdb

import com.ranhaveshush.mdb.model.api.MoviesPageResponse
import com.squareup.moshi.Json

/**
 * A TMDb [movies page response][MoviesPageResponse] object.
 */
data class TmdbMoviesPageResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "total_results") val totalResults: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "results") val results: List<TmdbMovieItemResponse>
) : MoviesPageResponse
