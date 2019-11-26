package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieDetailsBackdropLoader
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.MarginLinearItemDecoration
import com.ranhaveshush.mdb.viewmodel.HomeViewModel
import com.ranhaveshush.mdb.vo.MoviesCategory
import kotlinx.android.synthetic.main.fragment_home.imageView_featuredMovie
import kotlinx.android.synthetic.main.fragment_home.popular_movies
import kotlinx.android.synthetic.main.fragment_home.textView_featuredMovie
import kotlinx.android.synthetic.main.fragment_home.top_rated_movies
import kotlinx.android.synthetic.main.fragment_home.upcoming_movies

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels(
        factoryProducer = HomeViewModel.FactoryProducer.create()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFeaturedMovie()
        setupMovieCategories()
    }

    private fun setupFeaturedMovie() {
        val movieId = 475557

        val backdropLoader = MovieDetailsBackdropLoader {
            viewModel.getBackdropUrl(it)
        }

        viewModel.getMovieDetails(movieId).observe(viewLifecycleOwner) {
            it.data?.let { movieDetails ->
                textView_featuredMovie.text = movieDetails.title
                backdropLoader.load(movieDetails, imageView_featuredMovie)
            }
        }
    }

    private fun setupMovieCategories() {
        val moviesCategories = createMoviesCategories()
        moviesCategories.forEach { (view, category) ->
            bindMoviesCategoryToView(view, category)
        }
    }

    private fun createMoviesCategories(): Map<View, MoviesCategory> = mapOf(
        Pair(popular_movies, MoviesCategory(
            getString(R.string.fragment_popular_movies_title),
            { viewModel.popularMovies },
            { HomeFragmentDirections.actionHomeFragmentToPopularMoviesFragment() }
        )),
        Pair(top_rated_movies, MoviesCategory(
            getString(R.string.fragment_top_rated_movies_title),
            { viewModel.topRatedMovies },
            { HomeFragmentDirections.actionHomeFragmentToTopRatedMoviesFragment() }
        )),
        Pair(upcoming_movies, MoviesCategory(
            getString(R.string.fragment_upcoming_movies_title),
            { viewModel.upcomingMovies },
            { HomeFragmentDirections.actionHomeFragmentToUpcomingMoviesFragment() }
        )))

    private fun bindMoviesCategoryToView(
        view: View,
        category: MoviesCategory
    ) {
        val moviesListView = view as ViewGroup
        val moviesTitleTextView = moviesListView.findViewById<TextView>(R.id.textView_moviesTitle)
        val moviesRecyclerView = moviesListView.findViewById<RecyclerView>(R.id.recyclerView_movies)

        moviesTitleTextView.text = category.title
        moviesTitleTextView.setOnClickListener {
            val directions = category.getDirectionsToCategory()
            findNavController().navigate(directions)
        }

        val posterLoader = MovieItemPosterLoader {
            viewModel.getPosterUrl(it)
        }

        val movieItemMargin = resources.getDimension(R.dimen.item_movie_margin).toInt()
        moviesRecyclerView.addItemDecoration(MarginLinearItemDecoration(movieItemMargin))
        moviesRecyclerView.adapter = MoviesAdapter(posterLoader)

        category.getLiveMoviesList().observe(viewLifecycleOwner, Observer {
            val adapter = moviesRecyclerView.adapter as MoviesAdapter
            adapter.submitList(it)
        })
    }
}
