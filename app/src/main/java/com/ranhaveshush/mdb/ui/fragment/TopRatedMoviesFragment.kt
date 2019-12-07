package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import com.ranhaveshush.mdb.viewmodel.Category

class TopRatedMoviesFragment : MovieCategoryFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.category.value = Category.TOP_RATED
    }
}
