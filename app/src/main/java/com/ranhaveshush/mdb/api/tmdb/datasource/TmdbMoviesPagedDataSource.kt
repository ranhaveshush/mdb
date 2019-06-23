package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.PageKeyedDataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.*

abstract class TmdbMoviesPagedDataSource : PageKeyedDataSource<Int, MovieItem>() {
    abstract fun loadPage(page: Int): List<MovieItem>

    @WorkerThread
    abstract fun loadPageResponse(page: Int): TmdbMoviesPageResponse

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieItem>) {
        val moviesPageResponse = loadPageResponse(1)
        val data = moviesPageResponse.results.map { TmdbApi.converter.movieItemConverter().convert(it) }
        callback.onResult(data, 0, moviesPageResponse.totalResults, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        val page = params.key
        val data = loadPage(page)
        callback.onResult(data, page + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        // ignored, since we only ever append to our initial load
    }

    protected fun toRegion(locale: Locale): String = "${locale.isO3Language}_${locale.isO3Country}"
}
