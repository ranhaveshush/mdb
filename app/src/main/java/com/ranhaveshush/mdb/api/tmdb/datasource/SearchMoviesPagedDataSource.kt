package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem

class SearchMoviesPagedDataSource(
    private val api: TmdbApi,
    private val query: String
) : TmdbMoviesPagedDataSource() {
    override fun loadPage(page: Int): List<MovieItem> {
        val moviesPageResponse = loadPageResponse(page)
        return moviesPageResponse.results.map { TmdbApi.converter.movieItemConverter().convert(it) }
    }

    @WorkerThread
    override fun loadPageResponse(page: Int): TmdbMoviesPageResponse {
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
