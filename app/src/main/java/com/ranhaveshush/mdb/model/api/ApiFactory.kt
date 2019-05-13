package com.ranhaveshush.mdb.model.api

import com.ranhaveshush.mdb.model.api.tmdb.TmdbApi

/**
 * The API factory, a factory for [ClientApi] specific implementations
 * dependent on the given [ApiProvider].
 */
object ApiFactory {
    fun get(provider: ApiProvider): ClientApi =
        when (provider) {
            ApiProvider.TMDb -> TmdbApi
        }
}

