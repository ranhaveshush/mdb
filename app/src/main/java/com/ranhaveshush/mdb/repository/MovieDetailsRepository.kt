package com.ranhaveshush.mdb.repository

import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.EmptyApiResponse
import com.ranhaveshush.mdb.api.ErrorApiResponse
import com.ranhaveshush.mdb.api.SuccessApiResponse
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(client: ApiClient) : Repository(client) {
    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        emit(Resource.loading<MovieDetails>())

        val resource: Resource<MovieDetails> = when (val apiResponse = client.getDetails(movieId)) {
            is SuccessApiResponse -> Resource.success(apiResponse.data)
            is EmptyApiResponse -> Resource.empty<MovieDetails>()
            is ErrorApiResponse -> Resource.error<MovieDetails>(apiResponse.message)
        }

        emit(resource)
    }
}
