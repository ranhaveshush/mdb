package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.BuildConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A [TMDb](https://developers.themoviedb.org/3/) client API implementation.
 *
 * The Movie Database (TMDb) is a community built movies and TV database.
 * Every piece of data has been added by our amazing community dating back to 2008.
 * TMDb's strong international focus and breadth of data is largely unmatched and something we're incredibly proud of.
 * Put simply, we live and breathe community and that's precisely what makes us different.
 */
object TmdbApi {
    private const val BASE_URL = BuildConfig.TMDB_API_URL

    val service = createService(
        HttpUrl.parse(BASE_URL)!!,
        TmdbMovieService::class.java
    )

    private fun <ServiceT> createService(
        baseUrl: HttpUrl,
        serviceClass: Class<ServiceT>
    ): ServiceT {
        val client = OkHttpClient.Builder()
            .addInterceptor(TmdbInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}
