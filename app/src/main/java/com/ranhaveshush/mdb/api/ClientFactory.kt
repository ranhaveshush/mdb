package com.ranhaveshush.mdb.api

import com.ranhaveshush.mdb.api.tmdb.TmdbApi
import com.ranhaveshush.mdb.api.tmdb.TmdbClient
import java.util.Locale

/**
 * A factory for [ApiClient] specific implementations.
 */
object ClientFactory {
    val default: ApiClient = get(ApiProvider.TMDb)

    /**
     * Returns an [ApiClient] specific implementation,
     * dependent on the given [ApiProvider] and [Locale].
     *
     * @param provider The API provider identifier.
     * @param locale The optional locale, if not provided the default locale will be used.
     *
     * @return The requested [ApiClient] for the given [ApiProvider].
     */
    private fun get(provider: ApiProvider, locale: Locale = Locale.getDefault()): ApiClient =
        when (provider) {
            ApiProvider.TMDb -> TmdbClient(TmdbApi, locale)
        }
}
