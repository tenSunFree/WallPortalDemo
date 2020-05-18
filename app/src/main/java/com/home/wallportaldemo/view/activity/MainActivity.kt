package com.home.wallportaldemo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.home.wallportaldemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()
    }

    private fun initNavController() {
        val navController = findNavController(R.id.fragment)
        bottom_navigation_view.setupWithNavController(navController)
    }
}
