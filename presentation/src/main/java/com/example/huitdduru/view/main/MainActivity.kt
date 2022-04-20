package com.example.huitdduru.view.main

import android.os.Bundle
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityMainBinding
import com.example.huitdduru.view.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}