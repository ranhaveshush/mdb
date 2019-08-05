package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.*

/**
 * A upcoming movies [TmdbMoviesPagedDataSource] implementation,
 * loads upcoming movies page data.
 */
class UpcomingMoviesPagedDataSource(
    private val api: TmdbApi,
    private val locale: Locale
) : TmdbMoviesPagedDataSource() {
    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPage {
        val response = api.service.getUpcoming(locale.country, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> =
            UpcomingMoviesPagedDataSource(api, locale).map(TmdbMovieItemToMovieItemFunction())
    }
}
