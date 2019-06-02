package com.ranhaveshush.mdb.model.repository

import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.api.ClientApiFactory
import com.ranhaveshush.mdb.model.vo.MovieDetails
import java.util.Locale

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(
    provider: ApiProvider,
    locale: Locale = Locale.getDefault()
) {
    private val client: ClientApi = ClientApiFactory.get(provider)
    private val language: String = locale.toString()

    suspend fun getDetails(movieId: Int): MovieDetails {
        val movieDetailsResponse = client.getMovieService().getDetails(movieId, language).await()
        return client.getConverterFactory().movieDetailsConverter().convert(movieDetailsResponse)
    }
}
