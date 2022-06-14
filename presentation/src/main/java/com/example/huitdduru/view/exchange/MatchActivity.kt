package com.example.huitdduru.view.exchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.databinding.ActivityMatchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchActivity : BaseActivity<ActivityMatchBinding>(R.layout.activity_match) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        binding.activity = this

        if(intent.hasExtra("type"))
            matchType(intent.getIntExtra("type", 0))
    }

    private fun matchType(type: Int) = when(type) {
        0 -> replace(RandomMateFragment())
        1 -> replace(LocalMateFragment())
        else -> {}
    }

    fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.container, fragment)
            .commit()
    }
}