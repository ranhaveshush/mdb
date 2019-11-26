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
import com.ranhaveshush.mdb.ui.recyclerview.AutoSpanGridLayoutManager
import com.ranhaveshush.mdb.ui.recyclerview.MarginGridItemDecoration
import com.ranhaveshush.mdb.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.editText_searchQuery
import kotlinx.android.synthetic.main.fragment_search.imageView_searchClear
import kotlinx.android.synthetic.main.fragment_search.recyclerView_movies

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels(
        factoryProducer = SearchViewModel.FactoryProducer.create()
    )

    private val moviesAdapter = MoviesAdapter(MovieItemPosterLoader {
        viewModel.getPosterUrl(it)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.query.value = null

        editText_searchQuery.addTextChangedListener {
            viewModel.query.value = it

            imageView_searchClear.visibility =
                if (it.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
        }

        imageView_searchClear.setOnClickListener {
            editText_searchQuery.text = null
        }

        val movieItemWidth = resources.getDimension(R.dimen.item_movie_width).toInt()

        recyclerView_movies.layoutManager = AutoSpanGridLayoutManager(context, movieItemWidth)
        recyclerView_movies.addItemDecoration(MarginGridItemDecoration(movieItemWidth))
        recyclerView_movies.adapter = moviesAdapter

        viewModel.movies.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }
}
