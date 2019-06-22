package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.api.MovieService

/**
 * A TMDb movie service adapter,
 * adapts the detail [TmdbMovieService] interface to the policy [MovieService] interface.
 *
 * Notice: This adapter class is required since [TmdbMovieService] is a Retrofit interface,
 * and as such it cannot extend other interfaces.
 *
 * @param delegate The [TmdbMovieService] interface implementation, to delegate calls to.
 */
class TmdbMovieServiceAdapter(
    private val delegate: TmdbMovieService
) : MovieService {
    override suspend fun search(query: String, page: Int) = delegate.search(query, page)

    override suspend fun getPopular(region: String, page: Int) = delegate.getPopular(region, page)

    override suspend fun getTopRated(region: String, page: Int) = delegate.getTopRated(region, page)

    override suspend fun getUpcoming(region: String, page: Int) = delegate.getUpcoming(region, page)

    override suspend fun getDetails(movieId: Int, language: String) = delegate.getDetails(movieId, language)
}
