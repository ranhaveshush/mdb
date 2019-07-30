package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.Locale

/**
 * A upcoming movies [TmdbMoviesPagedDataSource] implementation,
 * loads upcoming movies page data.
 */
class UpcomingMoviesPagedDataSource(
    private val api: TmdbApi,
    locale: Locale
) : TmdbMoviesPagedDataSource() {
    private val region = toRegion(locale)

    @WorkerThread
    override fun requestPage(page: Int): TmdbMoviesPageResponse {
        val response = api.service.getUpcoming(region, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> = UpcomingMoviesPagedDataSource(api, locale)
    }
}
