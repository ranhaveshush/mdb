package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.AutoSpanGridLayoutManager
import com.ranhaveshush.mdb.ui.recyclerview.MarginGridItemDecoration
import com.ranhaveshush.mdb.viewmodel.Category
import com.ranhaveshush.mdb.viewmodel.MovieCategoryViewModel
import kotlinx.android.synthetic.main.fragment_movie_category.recyclerView_movies
import kotlinx.android.synthetic.main.fragment_movie_category.textView_categoryTitle

abstract class MovieCategoryFragment : Fragment(R.layout.fragment_movie_category) {
    protected abstract val category: Category

    private val viewModel: MovieCategoryViewModel by viewModels(
        factoryProducer = MovieCategoryViewModel.FactoryProducer.create()
    )

    private val moviesAdapter: MoviesAdapter =
        MoviesAdapter(MovieItemPosterLoader { viewModel.getPosterUrl(it) })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView_categoryTitle.text = category.name

        val movieItemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()
        val screenWidth = getDisplayMetrics().widthPixels

        recyclerView_movies.layoutManager = AutoSpanGridLayoutManager(context, movieItemWidth)
        recyclerView_movies.addItemDecoration(
            MarginGridItemDecoration(
                movieItemWidth,
                screenWidth
            )
        )
        recyclerView_movies.adapter = moviesAdapter

        val movies = viewModel.getMovies(category)

        movies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.submitList(it)
        })
    }

    private fun getDisplayMetrics(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        return displayMetrics
    }
}
