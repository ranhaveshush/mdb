package com.ranhaveshush.mdb.model

import com.ranhaveshush.mdb.model.dto.MovieDTO
import com.ranhaveshush.mdb.model.service.MovieService
import com.ranhaveshush.mdb.model.service.MovieServiceFactory
import com.ranhaveshush.mdb.model.service.MovieServiceProvider
import retrofit2.Call
import retrofit2.Response

/**
 * The movie repository an abstraction between the movie data sources and the app.
 */
class MovieRepository {
    private val movieService: MovieService = MovieServiceFactory.get(
        MovieServiceProvider.TMDb)

    fun search(query: String, onResult: (isSuccess: Boolean, response: Response<List<MovieDTO>>?) -> Unit) {
        movieService.search(query).enqueue(object : retrofit2.Callback<List<MovieDTO>> {
            override fun onResponse(call: Call<List<MovieDTO>>, response: Response<List<MovieDTO>>) {
                if (response.isSuccessful) onResult(true, response) else onResult(false, null)
            }

            override fun onFailure(call: Call<List<MovieDTO>>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    fun getDetails(movieId: Int, onResult: (isSuccess: Boolean, response: Response<MovieDTO>?) -> Unit) {
        movieService.getDetails(movieId).enqueue(object : retrofit2.Callback<MovieDTO> {
            override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                if (response.isSuccessful) onResult(true, response) else onResult(false, null)
            }

            override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                onResult(false, null)
            }
        })
    }
}
