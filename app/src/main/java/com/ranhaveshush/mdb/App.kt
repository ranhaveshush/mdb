package com.ranhaveshush.mdb

import android.app.Application
import android.os.StrictMode

/**
 * This [Application] class configures the [StrictMode] as early as possible.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        configStrictMode()
    }

    private fun configStrictMode() {
        // Configure thread policy
        val threadPolicy = StrictMode.ThreadPolicy.Builder().detectAll().build()
        StrictMode.setThreadPolicy(threadPolicy)

        // Configure VM policy
        val vmPolicy = StrictMode.VmPolicy.Builder().detectAll().build()
        StrictMode.setVmPolicy(vmPolicy)
    }
}
