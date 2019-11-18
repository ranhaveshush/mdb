package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.databinding.FragmentMovieDetailsBinding
import com.ranhaveshush.mdb.ui.image.MovieDetailsBackdropLoader
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels(
        factoryProducer = MovieDetailsViewModel.FactoryProducer.create()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater)

        val backdropLoader = MovieDetailsBackdropLoader {
            viewModel.getBackdropUrl(it)
        }

        viewModel.getDetails(args.movieId).observe(viewLifecycleOwner, Observer {
            binding.resource = it

            val movieDetails = it.data
            if (movieDetails != null) {
                backdropLoader.load(movieDetails, binding.imageViewMovieBackdrop)
            }
        })

        return binding.root
    }
}
