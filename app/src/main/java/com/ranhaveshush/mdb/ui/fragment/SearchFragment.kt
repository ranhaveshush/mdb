package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.ui.image.MovieItemPosterLoader
import com.ranhaveshush.mdb.ui.recyclerview.MarginItemDecoration
import com.ranhaveshush.mdb.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.editText_search
import kotlinx.android.synthetic.main.fragment_search.recyclerView_movies

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels(
        factoryProducer = SearchViewModel.FactoryProducer.create()
    )

    private val moviesAdapter = MoviesAdapter(MovieItemPosterLoader {
        viewModel.getPosterUrl(it)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editText_search.text = null
        editText_search.addTextChangedListener {
            viewModel.query.value = it
        }

        val movieItemMargin = resources.getDimension(R.dimen.item_movie_margin).toInt()
        recyclerView_movies.addItemDecoration(MarginItemDecoration(movieItemMargin))
        recyclerView_movies.adapter = moviesAdapter

        viewModel.movies.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }
}
