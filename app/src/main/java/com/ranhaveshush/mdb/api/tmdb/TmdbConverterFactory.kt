package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.api.Converter
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.MoviesPage

/**
 * A TMDb [Converter.Factory] implementation to convert TMDb response objects
 * to [value object][com.ranhaveshush.mdb.model.vo.ValueObject]'s.
 */
class TmdbConverterFactory : Converter.Factory {
    override fun moviesPageConverter(): Converter<Any, MoviesPage> =
        object : Converter<Any, MoviesPage> {
            override fun convert(value: Any): MoviesPage {
                val response = value as TmdbMoviesPageResponse

                val results = response.results.map { movieItemConverter().convert(it) }

                return MoviesPage(
                    response.page,
                    response.totalResults,
                    response.totalPages,
                    results
                )
            }
        }

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
