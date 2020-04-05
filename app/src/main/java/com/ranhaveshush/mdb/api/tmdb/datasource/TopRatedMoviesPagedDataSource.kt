package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.arch.core.util.Function
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieItem
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.Locale

/**
 * A top rated movies [TmdbMoviesPagedDataSource] implementation,
 * loads top rated movies page data.
 */
class TopRatedMoviesPagedDataSource(
    private val api: TmdbApi,
    private val locale: Locale
) : TmdbMoviesPagedDataSource() {
    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPage {
        val response = api.service.getTopRated(locale.country, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale,
        private val adapter: Function<TmdbMovieItem, MovieItem>
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> {
            return TopRatedMoviesPagedDataSource(api, locale).map(adapter)
        }
    }
}
