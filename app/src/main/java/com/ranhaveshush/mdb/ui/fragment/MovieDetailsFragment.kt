package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ranhaveshush.mdb.databinding.FragmentMovieDetailsBinding
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels(
        factoryProducer = MovieDetailsViewModel.FactoryProducer.create()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater)

        viewModel.getDetails(args.movieId).observe(this@MovieDetailsFragment, Observer {
            binding.resource = it
        })

        return binding.root
    }
}
