package com.ranhaveshush.mdb.model

import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.dto.MovieDTO
import retrofit2.Call
import retrofit2.Response

/**
 * The movie repository an abstraction between the movie data sources and the app.
 */
class MovieRepository(private val clientApi: ClientApi) {
    fun search(query: String, onResult: (isSuccess: Boolean, response: Response<List<MovieDTO>>?) -> Unit) {
        clientApi.getMovieService().search(query).enqueue(object : retrofit2.Callback<List<MovieDTO>> {
            override fun onResponse(call: Call<List<MovieDTO>>, response: Response<List<MovieDTO>>) {
                if (response.isSuccessful) onResult(true, response) else onResult(false, null)
            }

            override fun onFailure(call: Call<List<MovieDTO>>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    fun getDetails(movieId: Int, onResult: (isSuccess: Boolean, response: Response<MovieDTO>?) -> Unit) {
        clientApi.getMovieService().getDetails(movieId).enqueue(object : retrofit2.Callback<MovieDTO> {
            override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                if (response.isSuccessful) onResult(true, response) else onResult(false, null)
            }

            override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                onResult(false, null)
            }
        })
    }
}
