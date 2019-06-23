package com.ranhaveshush.mdb.repository

import androidx.lifecycle.LiveData
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.vo.MovieDetails

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(private val client: ApiClient) {
    fun getDetails(movieId: Int): LiveData<MovieDetails> = client.getDetails(movieId)
}
