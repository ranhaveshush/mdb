package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import com.ranhaveshush.mdb.viewmodel.Category

class PopularMoviesFragment : MovieCategoryFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.category.value = Category.POPULAR
    }
}
