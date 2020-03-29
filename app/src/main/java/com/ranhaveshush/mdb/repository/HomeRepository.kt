package com.ranhaveshush.mdb.repository

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.EmptyApiResponse
import com.ranhaveshush.mdb.api.ErrorApiResponse
import com.ranhaveshush.mdb.api.SuccessApiResponse
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The home repository.
 * An abstraction layer between the movies data sources and the app.
 */
class HomeRepository(client: ApiClient) : Repository(client) {
    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()

    fun getTopRated(): DataSource.Factory<Int, MovieItem> = client.getTopRated()

    fun getUpcoming(): DataSource.Factory<Int, MovieItem> = client.getUpcoming()

    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        emit(Resource.loading<MovieDetails>())

        val resource: Resource<MovieDetails> = when (val apiResponse = client.getDetails(movieId)) {
            is SuccessApiResponse -> Resource.success(apiResponse.data)
            is EmptyApiResponse -> Resource.empty<MovieDetails>()
            is ErrorApiResponse -> Resource.error<MovieDetails>(apiResponse.message)
        }

        emit(resource)
    }

    fun getPosterUrl(movieItem: MovieItem): String = client.getPosterUrl(movieItem)

    fun getBackdropUrl(movieDetails: MovieDetails): String = client.getBackdropUrl(movieDetails)
}
