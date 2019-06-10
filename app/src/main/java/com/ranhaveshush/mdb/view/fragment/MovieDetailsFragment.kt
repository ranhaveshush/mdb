package com.ranhaveshush.mdb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.model.vo.Status
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetails(args.movieId).observe(this) {
            when (it.status) {
                Status.LOADING -> progressBar_contentLoading.show()
                Status.SUCCESS -> {
                    progressBar_contentLoading.hide()
                    textView_movieDetails.text = it.data.toString()
                }
                Status.ERROR -> {
                    progressBar_contentLoading.hide()
                    showError(it.message)
                }
            }
        }
    }

    private fun showError(message: String?) {
        textView_errorMessage.text = message
    }
}
