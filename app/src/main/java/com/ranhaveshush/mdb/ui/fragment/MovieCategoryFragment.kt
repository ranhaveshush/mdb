package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.databinding.FragmentMovieCategoryBinding
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.AutoSpanGridLayoutManager
import com.ranhaveshush.mdb.ui.recyclerview.MarginGridItemDecoration
import com.ranhaveshush.mdb.util.InjectorUtils
import com.ranhaveshush.mdb.viewmodel.MovieCategoryViewModel

abstract class MovieCategoryFragment : Fragment(R.layout.fragment_movie_category) {
    protected val viewModel: MovieCategoryViewModel by viewModels {
        InjectorUtils.provideMovieCategoryViewModelFactory()
    }

    private val moviesAdapter: MoviesAdapter =
        MoviesAdapter(MovieItemPosterLoader { viewModel.getPosterUrl(it) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieCategoryBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        val movieItemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()
        val screenWidth = getDisplayMetrics().widthPixels

        binding.recyclerViewMovies.layoutManager =
            AutoSpanGridLayoutManager(context, movieItemWidth)
        binding.recyclerViewMovies.addItemDecoration(
            MarginGridItemDecoration(
                movieItemWidth,
                screenWidth
            )
        )
        binding.recyclerViewMovies.adapter = moviesAdapter

        return binding.root
    }

    private fun getDisplayMetrics(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        return displayMetrics
    }
}
