package com.ranhaveshush.mdb.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The movies repository.
 * An abstraction layer between movies data sources and the app.
 */
class MoviesRepository(private val client: ApiClient) {
    private val pageSize = 50

    fun search(query: String): LiveData<PagedList<MovieItem>> {
        val dataSourceFactory = client.search(query)
        return LivePagedListBuilder(dataSourceFactory, pageSize).build()
    }

    fun getPopular(): LiveData<PagedList<MovieItem>> {
        val dataSourceFactory = client.getPopular()
        return LivePagedListBuilder(dataSourceFactory, pageSize).build()
    }

    fun getTopRated(): LiveData<PagedList<MovieItem>> {
        val dataSourceFactory = client.getTopRated()
        return LivePagedListBuilder(dataSourceFactory, pageSize).build()
    }

    fun getUpcoming(): LiveData<PagedList<MovieItem>> {
        val dataSourceFactory = client.getUpcoming()
        return LivePagedListBuilder(dataSourceFactory, pageSize).build()
    }
}
