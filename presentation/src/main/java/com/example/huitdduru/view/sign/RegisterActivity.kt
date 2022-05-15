package com.example.huitdduru.view.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityRegisterBinding
import com.example.huitdduru.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            add(R.id.container, InfoFragment())
        }
    }

    fun replace(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            replace(R.id.container, fragment)
        }
    }
}