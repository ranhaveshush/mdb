package com.ranhaveshush.mdb.api.tmdb

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ranhaveshush.mdb.BuildConfig
import com.ranhaveshush.mdb.api.ClientApi
import com.ranhaveshush.mdb.api.Converter
import com.ranhaveshush.mdb.api.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A [TMDb](https://developers.themoviedb.org/3/) [ClientApi] implementation.
 *
 * The Movie Database (TMDb) is a community built MoviesViewModelmovie and TV database.
 * Every piece of data has been added by our amazing community dating back to 2008.
 * TMDb's strong international focus and breadth of data is largely unmatched and something we're incredibly proud of.
 * Put simply, we live and breathe community and that's precisely what makes us different.
 */
object TmdbApi : ClientApi {
    private val movieService: MovieService = createMovieService()

    private val converterFactory: Converter.Factory = TmdbConverterFactory()

    override fun getMovieService(): MovieService = movieService

    override fun getConverterFactory(): Converter.Factory = converterFactory

    private fun createMovieService() = TmdbMovieServiceAdapter(retrofit().create(TmdbMovieService::class.java))

    private fun retrofit() = Retrofit.Builder()
        .client(client())
        .baseUrl(BuildConfig.TMDB_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private fun client() = OkHttpClient.Builder()
        .addInterceptor(TmdbInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()
}
