package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.PageKeyedDataSource
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieItem
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import java.util.Locale

/**
 * A TMDb [PageKeyedDataSource] base implementation.
 * Specific data source implementations need to extend this abstract class
 * and override [requestPage] to provide their relevant page data.
 */
abstract class TmdbMoviesPagedDataSource : PageKeyedDataSource<Int, TmdbMovieItem>() {
    private var totalResults: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TmdbMovieItem>
    ) {
        val data = loadPage(1)
        callback.onResult(data, 0, totalResults, 1, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TmdbMovieItem>) {
        val page = params.key
        val data = loadPage(page)
        callback.onResult(data, page + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TmdbMovieItem>) {
        // ignored, since we only ever append to our initial load.
    }

    private fun loadPage(page: Int): List<TmdbMovieItem> {
        val moviesPage = requestPage(page)
        totalResults = moviesPage.totalResults

        return moviesPage.results
    }

    @WorkerThread
    abstract fun requestPage(page: Int): TmdbMoviesPage

    protected fun toRegion(locale: Locale): String = locale.country
}
