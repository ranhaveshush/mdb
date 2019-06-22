package com.ranhaveshush.mdb.repository

import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.api.ClientApiFactory
import com.ranhaveshush.mdb.vo.MoviesPage
import java.util.Locale

/**
 * The movies repository.
 * An abstraction layer between movies data sources and the app.
 */
class MoviesRepository(
    provider: ApiProvider,
    private val locale: Locale = Locale.getDefault()
) {
    private val client = ClientApiFactory.get(provider)
    private val region = locale.toString()

    suspend fun search(query: String, page: Int): MoviesPage {
        val moviesPageResponse = client.getMovieService().search(query, page).await()
        return client.getConverterFactory().moviesPageConverter().convert(moviesPageResponse)
    }

    suspend fun getPopular(page: Int): MoviesPage {
        val moviesPageResponse = client.getMovieService().getPopular(region, page).await()
        return client.getConverterFactory().moviesPageConverter().convert(moviesPageResponse)
    }

    suspend fun getTopRated(page: Int): MoviesPage {
        val moviesPageResponse = client.getMovieService().getTopRated(region, page).await()
        return client.getConverterFactory().moviesPageConverter().convert(moviesPageResponse)
    }

    suspend fun getUpcoming(page: Int): MoviesPage {
        // Upcoming endpoint doesn't support the region parameter with language and country,
        // but just country.
        val moviesPageResponse = client.getMovieService().getUpcoming(locale.country, page).await()
        return client.getConverterFactory().moviesPageConverter().convert(moviesPageResponse)
    }
}
