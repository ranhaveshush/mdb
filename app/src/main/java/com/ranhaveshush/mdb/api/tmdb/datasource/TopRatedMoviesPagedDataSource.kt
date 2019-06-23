package com.ranhaveshush.mdb.api.tmdb.datasource

import androidx.annotation.WorkerThread
import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMoviesPageResponse
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.*

class TopRatedMoviesPagedDataSource(
    private val api: TmdbApi,
    locale: Locale
) : TmdbMoviesPagedDataSource() {
    private val region = toRegion(locale)

    override fun loadPage(page: Int): List<MovieItem> {
        val moviesPageResponse = loadPageResponse(page)
        return moviesPageResponse.results.map { TmdbApi.converter.movieItemConverter().convert(it) }
    }

    @WorkerThread
    override fun loadPageResponse(page: Int): TmdbMoviesPageResponse {
        val response = api.service.getTopRated(region, page).execute()
        return response.body()!!
    }

    class Factory(
        private val api: TmdbApi,
        private val locale: Locale
    ) : DataSource.Factory<Int, MovieItem>() {
        override fun create(): DataSource<Int, MovieItem> = TopRatedMoviesPagedDataSource(api, locale)
    }
}
