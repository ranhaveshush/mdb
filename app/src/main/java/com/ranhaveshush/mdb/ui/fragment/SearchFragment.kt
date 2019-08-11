package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.viewmodel.SearchViewModel
import com.ranhaveshush.mdb.viewmodel.SearchViewModelFactory

@Suppress("EmptyClassBlock")
class SearchFragment : Fragment(R.layout.fragment_search) {
    val viewModel: SearchViewModel by viewModels(
        factoryProducer = { SearchViewModelFactory() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TODO("Pre load the search screen with popular movies and add the search logic.")
    }
}
