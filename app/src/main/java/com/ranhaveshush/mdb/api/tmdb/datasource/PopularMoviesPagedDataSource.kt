package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.*

/**
 * A popular movies [TmdbMoviesPagedDataSource] implementation,
 * loads popular movies page data.
 */
class PopularMoviesPagedDataSource(
    private val api: TmdbApi,
    private val locale: Locale
) : TmdbMoviesPagedDataSource() {
    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPage {
        val response = api.service.getPopular(locale.country, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> =
            PopularMoviesPagedDataSource(api, locale).map(TmdbMovieItemToMovieItemFunction())
    }
}
