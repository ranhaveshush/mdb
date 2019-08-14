package com.ranhaveshush.mdb.api.tmdb.data

import com.squareup.moshi.Json

/**
 * A [TMDb API system wide configuration](https://developers.themoviedb.org/3/configuration/get-api-configuration) response object.
 * Some elements of the API require some knowledge of this configuration data.
 * The purpose of this is to try and keep the actual API responses as light as possible.
 * It is recommended you cache this data within your application and check for updates every few days.
 */
data class TmdbConfiguration(
    @field:Json(name = "images") val images: TmdbImages,
    @field:Json(name = "change_keys") val changeKeys: List<String>
)

/**
 * A TMDb API images configuration response object been used within the [TmdbConfiguration] response object.
 */
data class TmdbImages(
    @field:Json(name = "base_url") val baseUrl: String,
    @field:Json(name = "secure_base_url") val secureBaseUrl: String,
    @field:Json(name = "backdrop_sizes") val backdropSizes: List<String>,
    @field:Json(name = "logo_sizes") val logoSizes: List<String>,
    @field:Json(name = "poster_sizes") val posterSizes: List<String>,
    @field:Json(name = "profile_sizes") val profileSizes: List<String>,
    @field:Json(name = "still_sizes") val stillSizes: List<String>
)
