package com.example.huitdduru.view.exchange

import android.os.Bundle
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.databinding.ActivityMatchBinding

class MatchActivity : BaseActivity<ActivityMatchBinding>(R.layout.activity_match) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        binding.activity = this
    }
}