package com.ranhaveshush.mdb.model.service.tmdb

import com.ranhaveshush.mdb.model.dto.MovieDTO
import com.ranhaveshush.mdb.model.service.MovieService
import retrofit2.Call

/**
 * The TMDb service adapter,
 * adapts the detail [TmdbService] interface to the policy [MovieService] interface.
 *
 * @param delegate The [TmdbService] interface implementation, to delegate calls to.
 */
class TmdbServiceAdapter(private val delegate: TmdbService) : MovieService {
    override fun search(query: String): Call<List<MovieDTO>> = delegate.search(query)
    override fun getDetails(movieId: Int): Call<MovieDTO> = delegate.getDetails(movieId)
}