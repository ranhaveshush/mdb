package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.Locale

/**
 * A top rated movies [TmdbMoviesPagedDataSource] implementation,
 * loads top rated movies page data.
 */
class TopRatedMoviesPagedDataSource(
    private val api: TmdbApi,
    locale: Locale
) : TmdbMoviesPagedDataSource() {
    private val region = toRegion(locale)

    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPage {
        val response = api.service.getTopRated(region, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> =
            TopRatedMoviesPagedDataSource(api, locale).map(TmdbMovieItemToMovieItemFunction())
    }
}
