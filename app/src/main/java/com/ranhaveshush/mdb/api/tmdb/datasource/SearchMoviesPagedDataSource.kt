package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * A movies search [TmdbMoviesPagedDataSource] implementation,
 * loads movies search page data.
 */
class SearchMoviesPagedDataSource(
    private val api: TmdbApi,
    private val query: String
) : TmdbMoviesPagedDataSource() {
    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPageResponse {
        val response = api.service.search(query, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val query: String
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> = SearchMoviesPagedDataSource(api, query)
    }
}
