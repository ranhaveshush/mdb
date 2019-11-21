package com.ranhaveshush.mdb.repository

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The movie category repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieCategoryRepository(client: ApiClient) : Repository(client) {
    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()

    fun getTopRated(): DataSource.Factory<Int, MovieItem> = client.getTopRated()

    fun getUpcoming(): DataSource.Factory<Int, MovieItem> = client.getUpcoming()

    fun getPosterUrl(movieItem: MovieItem): String = client.getPosterUrl(movieItem)
}