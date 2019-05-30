package com.ranhaveshush.mdb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ranhaveshush.mdb.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
