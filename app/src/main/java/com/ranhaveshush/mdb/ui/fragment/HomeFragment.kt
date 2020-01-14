package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.databinding.FragmentHomeBinding
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.MarginLinearItemDecoration
import com.ranhaveshush.mdb.util.InjectorUtils
import com.ranhaveshush.mdb.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels {
        InjectorUtils.provideHomeViewModelFactory()
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        binding.popularMoviesDirections =
            HomeFragmentDirections.actionHomeFragmentToPopularMoviesFragment()
        binding.topRatedMoviesDirections =
            HomeFragmentDirections.actionHomeFragmentToTopRatedMoviesFragment()
        binding.upcomingMoviesDirections =
            HomeFragmentDirections.actionHomeFragmentToUpcomingMoviesFragment()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieItemMargin = resources.getDimension(R.dimen.item_movie_margin).toInt()
        val posterLoader = MovieItemPosterLoader { viewModel.getPosterUrl(it) }

        binding.popularMovies.recyclerViewMovies.adapter = MoviesAdapter(posterLoader)
        binding.popularMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )

        binding.topRatedMovies.recyclerViewMovies.adapter = MoviesAdapter(posterLoader)
        binding.topRatedMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )

        binding.upcomingMovies.recyclerViewMovies.adapter = MoviesAdapter(posterLoader)
        binding.upcomingMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )
    }
}
