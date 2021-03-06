package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.databinding.FragmentMovieDetailsBinding
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels {
        appComponent.movieDetailsViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.resource = viewModel.getDetails(args.movieId)

        return binding.root
    }
}
