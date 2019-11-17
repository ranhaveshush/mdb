package com.ranhaveshush.mdb.ui.image

import android.widget.ImageView
import coil.api.load
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * The movie item poster loader,
 * loads the image from the movie poster URL to the provided image view.
 *
 * @param buildPosterUrl Builds the movie poster full URL from a given [MovieItem]
 */
class MovieItemPosterLoader(
    private val buildPosterUrl: (MovieItem) -> String
) {
    /**
     * Loads the poster image from the [MovieItem] poster URL to the provided [ImageView].
     */
    fun load(movieItem: MovieItem, imageView: ImageView) {
        val posterUrl = buildPosterUrl(movieItem)
        imageView.load(posterUrl)
    }
}
