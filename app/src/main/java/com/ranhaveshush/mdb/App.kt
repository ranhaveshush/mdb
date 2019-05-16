package com.ranhaveshush.mdb

import android.app.Application
import android.os.Build
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
        val threadPolicyBuilder = StrictMode.ThreadPolicy.Builder()
        threadPolicyBuilder.detectNetwork()
        threadPolicyBuilder.detectCustomSlowCalls()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            threadPolicyBuilder.detectResourceMismatches()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            threadPolicyBuilder.detectUnbufferedIo()
        }
        StrictMode.setThreadPolicy(threadPolicyBuilder.build())

        // Configure VM policy
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().build())
    }
}
