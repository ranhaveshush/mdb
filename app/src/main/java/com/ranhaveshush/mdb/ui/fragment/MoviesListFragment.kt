package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranhaveshush.mdb.MainNavDirections
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MoviesViewModel
import com.ranhaveshush.mdb.vo.MoviesPage
import com.ranhaveshush.mdb.vo.Resource
import com.ranhaveshush.mdb.vo.Status
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_movies_list.*

abstract class MoviesListFragment : Fragment() {
    protected lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMoviesList(1).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> progressBar_contentLoading.show()
                Status.SUCCESS -> {
                    progressBar_contentLoading.hide()
                    recyclerView_movies.layoutManager = LinearLayoutManager(context)
                    recyclerView_movies.adapter = MoviesAdapter(it.data!!.results) { movieId, movieTitle ->
                        presentMovieDetails(movieId, movieTitle)
                    }
                }
                Status.ERROR -> {
                    progressBar_contentLoading.hide()
                    showError(it.message)
                }
            }
        })
    }

    protected abstract fun getMoviesList(page: Int): LiveData<Resource<MoviesPage>>

    private fun presentMovieDetails(movieId: Int, movieTitle: String) {
        val action = MainNavDirections.actionGlobalMovieDetailsFragment(movieId, movieTitle)
        findNavController().navigate(action)
    }

    private fun showError(message: String?) {
        textView_errorMessage.text = message
    }
}
