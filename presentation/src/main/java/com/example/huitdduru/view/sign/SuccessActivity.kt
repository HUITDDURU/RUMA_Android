package com.example.huitdduru.view.sign

import android.os.Bundle
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivitySuccessBinding
import com.example.huitdduru.base.BaseActivity

class SuccessActivity : BaseActivity<ActivitySuccessBinding>(R.layout.activity_success) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
    }
}