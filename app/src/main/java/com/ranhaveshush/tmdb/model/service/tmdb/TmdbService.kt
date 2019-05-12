package com.ranhaveshush.tmdb.model.service.tmdb

import com.ranhaveshush.tmdb.BuildConfig
import com.ranhaveshush.tmdb.model.dto.MovieDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * [The Movie Database API](https://developers.themoviedb.org/3/getting-started/search-and-query-for-details) service.
 *
 * The Movie Database (TMDb) is a community built movie and TV database.
 * Every piece of data has been added by our amazing community dating back to 2008.
 * TMDb's strong international focus and breadth of data is largely unmatched and something we're incredibly proud of.
 * Put simply, we live and breathe community and that's precisely what makes us different.
 */
interface TmdbService {
    @GET("search/movie")
    fun search(@Query("query") query: String): Call<List<MovieDTO>>

    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") movieId: Int): Call<MovieDTO>

    companion object {
        private var SERVICE: TmdbService? = null

        fun get(): TmdbService = SERVICE
            ?: retrofit().create(TmdbService::class.java)

        private fun retrofit(): Retrofit = Retrofit.Builder()
            .client(client())
            .baseUrl(BuildConfig.TMDB_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        private fun client(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(TmdbInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }
}
