package com.ranhaveshush.mdb.repository

import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.flow.Flow

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(client: ApiClient) : Repository(client) {
    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> = client.getDetails(movieId)

    fun getBackdropUrl(movieDetails: MovieDetails): String = client.getBackdropUrl(movieDetails)
}
