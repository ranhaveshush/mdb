package com.ranhaveshush.mdb.ui.fragment

import androidx.fragment.app.Fragment
import com.ranhaveshush.mdb.di.AppComponent
import com.ranhaveshush.mdb.ui.appComponent

val Fragment.appComponent: AppComponent
    get() = requireActivity().appComponent
