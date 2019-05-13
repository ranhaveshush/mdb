package com.ranhaveshush.mdb.model.api.tmdb

import com.ranhaveshush.mdb.model.dto.MovieDTO
import com.ranhaveshush.mdb.model.api.MovieService
import retrofit2.Call

/**
 * The TMDb movie service adapter,
 * adapts the detail [TmdbMovieService] interface to the policy [MovieService] interface.
 *
 * Notice: This adapter class is required since [TmdbMovieService] is a Retrofit interface,
 * and as such it cannot extend other interfaces.
 *
 * @param delegate The [TmdbMovieService] interface implementation, to delegate calls to.
 */
class TmdbMovieServiceAdapter(private val delegate: TmdbMovieService) : MovieService {
    override fun search(query: String): Call<List<MovieDTO>> = delegate.search(query)
    override fun getDetails(movieId: Int): Call<MovieDTO> = delegate.getDetails(movieId)
}