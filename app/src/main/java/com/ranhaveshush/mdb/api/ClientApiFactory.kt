package com.ranhaveshush.mdb.api

import com.ranhaveshush.mdb.api.tmdb.TmdbApi

/**
 * The API factory, a factory for [ClientApi] specific implementations
 * dependent on the given [ApiProvider].
 */
object ClientApiFactory {
    fun get(provider: ApiProvider): ClientApi =
        when (provider) {
            ApiProvider.TMDb -> TmdbApi
        }
}
