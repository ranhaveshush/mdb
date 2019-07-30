package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.api.Converter
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMovieDetailsResponse
import com.ranhaveshush.mdb.api.tmdb.response.TmdbMovieItemResponse
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * A TMDb [Converter.Factory] implementation to convert TMDb response objects
 * to [value object][com.ranhaveshush.mdb.vo.ValueObject]'s.
 */
// TODO: 7/30/19 Delete after changing implementation to DataSource.map(Function).
class TmdbConverterFactory : Converter.Factory {
    override fun movieItemConverter(): Converter<Any, MovieItem> =
        object : Converter<Any, MovieItem> {
            override fun convert(value: Any): MovieItem {
                val response = value as TmdbMovieItemResponse

                return MovieItem(
                    response.id,
                    response.title,
                    response.voteAverage,
                    response.voteCount,
                    response.posterPath
                )
            }
        }

    override fun movieDetailsConverter(): Converter<Any, MovieDetails> =
        object : Converter<Any, MovieDetails> {
            override fun convert(value: Any): MovieDetails {
                val response = value as TmdbMovieDetailsResponse

                return MovieDetails(
                    response.id,
                    response.title,
                    response.overview,
                    response.releaseDate,
                    response.runtime,
                    response.status,
                    response.voteAverage,
                    response.voteCount,
                    response.posterPath
                )
            }
        }
}
