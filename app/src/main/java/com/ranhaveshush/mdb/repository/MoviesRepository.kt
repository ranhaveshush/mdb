package com.ranhaveshush.mdb.repository

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The movies repository.
 * An abstraction layer between movies data sources and the app.
 */
class MoviesRepository(private val client: ApiClient) {
    fun search(query: String): DataSource.Factory<Int, MovieItem> = client.search(query)

    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()

    fun getTopRated(): DataSource.Factory<Int, MovieItem> = client.getTopRated()

    fun getUpcoming(): DataSource.Factory<Int, MovieItem> = client.getUpcoming()
}
