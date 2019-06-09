package com.ranhaveshush.mdb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.model.vo.Status
import com.ranhaveshush.mdb.view.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTopRated(1).observe(this) {
            when (it.status) {
                Status.LOADING -> progressBar_contentLoading.show()
                Status.SUCCESS -> {
                    progressBar_contentLoading.hide()
                    recyclerView_movies.layoutManager = LinearLayoutManager(context)
                    recyclerView_movies.adapter = MoviesAdapter(it.data!!.results)
                }
                Status.ERROR -> {
                    progressBar_contentLoading.hide()
                    showError(it.message)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        findNavController().currentDestination?.label = "Top Rated"
    }

    private fun showError(message: String?) {
        textView_errorMessage.text = message
    }
}
