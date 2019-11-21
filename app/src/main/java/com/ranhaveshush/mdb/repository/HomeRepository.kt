package com.ranhaveshush.mdb.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.Resource

/**
 * The home repository.
 * An abstraction layer between the movies data sources and the app.
 */
class HomeRepository(client: ApiClient) : Repository(client) {
    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()

    fun getTopRated(): DataSource.Factory<Int, MovieItem> = client.getTopRated()

    fun getUpcoming(): DataSource.Factory<Int, MovieItem> = client.getUpcoming()

    suspend fun getDetails(movieId: Int): LiveData<Resource<MovieDetails>> =
        client.getDetails(movieId)

    fun getPosterUrl(movieItem: MovieItem): String = client.getPosterUrl(movieItem)

    fun getBackdropUrl(movieDetails: MovieDetails): String = client.getBackdropUrl(movieDetails)
}