package com.ranhaveshush.mdb.repository

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The home repository.
 * An abstraction layer between the movies data sources and the app.
 */
class HomeRepository(client: ApiClient) : Repository(client) {
    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()

    fun getTopRated(): DataSource.Factory<Int, MovieItem> = client.getTopRated()

    fun getUpcoming(): DataSource.Factory<Int, MovieItem> = client.getUpcoming()

    fun getPosterUrl(movieItem: MovieItem): String = client.getPosterUrl(movieItem)
}
