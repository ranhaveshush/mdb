package com.ranhaveshush.mdb.model.repository

import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.api.ClientApiFactory
import com.ranhaveshush.mdb.model.vo.MoviesPage
import java.util.Locale

/**
 * The movies repository.
 * An abstraction layer between movies data sources and the app.
 */
class MoviesRepository(
    provider: ApiProvider,
    locale: Locale = Locale.getDefault()
) {
    private val client: ClientApi = ClientApiFactory.get(provider)
    private val region: String = locale.toString()

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
        val moviesPageResponse = client.getMovieService().getUpcoming(region, page).await()
        return client.getConverterFactory().moviesPageConverter().convert(moviesPageResponse)
    }
}
