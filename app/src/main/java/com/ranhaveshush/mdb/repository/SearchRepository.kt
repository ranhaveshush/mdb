package com.ranhaveshush.mdb.repository

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The search repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class SearchRepository(client: ApiClient) : Repository(client) {
    fun search(query: String): DataSource.Factory<Int, MovieItem> = client.search(query)

    fun getPopular(): DataSource.Factory<Int, MovieItem> = client.getPopular()
}
