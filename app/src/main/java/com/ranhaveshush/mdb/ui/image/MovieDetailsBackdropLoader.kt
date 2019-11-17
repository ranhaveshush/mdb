package com.ranhaveshush.mdb.ui.image

import android.widget.ImageView
import coil.api.load
import com.ranhaveshush.mdb.vo.MovieDetails

/**
 * The movie details backdrop loader,
 * loads the image from the movie backdrop URL to the provided image view.
 *
 * @param buildBackdropUrl Builds the movie backdrop full URL from a given [MovieDetails]
 */
class MovieDetailsBackdropLoader(
    private val buildBackdropUrl: (MovieDetails) -> String
) {
    /**
     * Loads the backdrop image from the [MovieDetails] backdrop URL to the provided [ImageView].
     */
    fun load(movieDetails: MovieDetails, imageView: ImageView) {
        val backdropUrl = buildBackdropUrl(movieDetails)
        imageView.load(backdropUrl)
    }
}
