package com.example.huitdduru.view.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.databinding.ActivityDiaryBinding

class DiaryActivity : BaseActivity<ActivityDiaryBinding>(R.layout.activity_diary) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.contentTv.movementMethod = ScrollingMovementMethod()
    }
}