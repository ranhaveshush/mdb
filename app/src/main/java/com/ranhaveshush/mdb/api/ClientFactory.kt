package com.ranhaveshush.mdb.api

import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.TmdbClient
import java.util.Locale

/**
 * The API factory, a factory for [ApiClient] specific implementations
 * dependent on the given [ApiProvider] and [Locale].
 */
object ClientFactory {
    fun get(provider: ApiProvider, locale: Locale = Locale.getDefault()): ApiClient =
        when (provider) {
            ApiProvider.TMDb -> TmdbClient(TmdbApi, locale)
        }
}
