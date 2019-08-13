package com.ranhaveshush.mdb.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.viewmodel.SearchViewModel

@Suppress("EmptyClassBlock")
class SearchFragment : Fragment(R.layout.fragment_search) {
    val viewModel: SearchViewModel by viewModels(
        factoryProducer = SearchViewModel.FactoryProducer.create()
    )

}
