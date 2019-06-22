package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * A TMDb [OkHttp](https://square.github.io/okhttp/) [Interceptor].
 *
 * Appends the TMDb api key query parameter name and value to every HTTP request.
 */
class TmdbInterceptor(
    private val apiKeyName: String = BuildConfig.TMDB_API_KEY_NAME,
    private val apiKeyValue: String = BuildConfig.TMDB_API_KEY_VALUE
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(apiKeyName, apiKeyValue)
            .build()

        val newRequest = originalRequest.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}
