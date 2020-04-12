package com.ranhaveshush.mdb.ui

import android.app.Activity
import com.ranhaveshush.mdb.App
import com.ranhaveshush.mdb.di.AppComponent

val Activity.appComponent: AppComponent
    get() = (application as App).appComponent
