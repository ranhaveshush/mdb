package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.PageKeyedDataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.Locale

/**
 * A TMDb [PageKeyedDataSource] base implementation.
 * Specific data source implementations need to extend this abstract class
 * and override [requestPage] to provide their relevant page data.
 */
abstract class TmdbMoviesPagedDataSource : PageKeyedDataSource<Int, MovieItem>() {
    private var totalResults: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieItem>) {
        val data = loadPage(1)
        callback.onResult(data, 0, totalResults, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        val page = params.key
        val data = loadPage(page)
        callback.onResult(data, page + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        // ignored, since we only ever append to our initial load.
    }

    private fun loadPage(page: Int): List<MovieItem> {
        val moviesPageResponse = requestPage(page)
        totalResults = moviesPageResponse.totalResults
        // TODO: 7/30/19 change implementation to DataSource.map(Function) and delete the converter.
        return moviesPageResponse.results.map { TmdbApi.converter.movieItemConverter().convert(it) }
    }

    @WorkerThread
    abstract fun requestPage(page: Int): TmdbMoviesPageResponse

    protected fun toRegion(locale: Locale): String = "${locale.language}_${locale.country}"
}
