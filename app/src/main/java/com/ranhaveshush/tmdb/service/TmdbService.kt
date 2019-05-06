package com.ranhaveshush.tmdb.service

import com.ranhaveshush.tmdb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
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
internal interface TmdbService {
    /**
     * Search for a movie by a given query string.
     *
     * @param query The query string, search keywords separated with +.
     * @return
     */
    @GET("search/movie")
    fun search(@Query("query") query: String): Call<ResponseBody>

    /**
     * Retrieve movie details for a given movie ID.
     *
     * @param movieId The TMDb movie identifier.
     * @return
     */
    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") movieId: Int): Call<ResponseBody>

    companion object TmdbServiceImpl {
        fun get(): TmdbService = SERVICE

        private val SERVICE: TmdbService = retrofit().create(TmdbService::class.java)

        private fun retrofit(): Retrofit = Retrofit.Builder()
            .client(client())
            .baseUrl(BuildConfig.TMDB_API_URL)
            .build()

        private fun client(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(TmdbInterceptor())
            .build()
    }
}
