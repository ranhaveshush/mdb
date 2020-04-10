package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranhaveshush.mdb.App
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.databinding.FragmentHomeBinding
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.recyclerview.MarginLinearItemDecoration
import com.ranhaveshush.mdb.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels {
        (context?.applicationContext as App).appComponent.homeViewModelFactory()
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

        binding.popularMovies.recyclerViewMovies.adapter = MoviesAdapter()
        binding.popularMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )

        binding.topRatedMovies.recyclerViewMovies.adapter = MoviesAdapter()
        binding.topRatedMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )

        binding.upcomingMovies.recyclerViewMovies.adapter = MoviesAdapter()
        binding.upcomingMovies.recyclerViewMovies.addItemDecoration(
            MarginLinearItemDecoration(
                movieItemMargin
            )
        )
    }
}
