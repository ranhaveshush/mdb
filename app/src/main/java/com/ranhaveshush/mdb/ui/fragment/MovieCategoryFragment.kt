package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.MarginItemDecoration
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

        val movieItemMargin = resources.getDimension(R.dimen.item_movie_margin).toInt()
        recyclerView_movies.addItemDecoration(MarginItemDecoration(movieItemMargin))
        recyclerView_movies.adapter = moviesAdapter

        val movies = viewModel.getMovies(category)

        movies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.submitList(it)
        })
    }
}
