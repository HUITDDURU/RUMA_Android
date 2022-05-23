package com.example.huitdduru.view.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityMainBinding
import com.example.huitdduru.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment
        val navController = navFragment.navController

        NavigationUI.setupWithNavController(binding.navView, navController)
    }
}