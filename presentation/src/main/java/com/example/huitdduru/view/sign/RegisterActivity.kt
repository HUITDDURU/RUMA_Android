package com.example.huitdduru.view.sign

import android.os.Bundle
import androidx.fragment.app.commit
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityRegisterBinding
import com.example.huitdduru.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            add(R.id.container, InfoFragment())
            add(R.id.container, CertifyFragment())
            add(R.id.container, UserFragment())
        }
    }
}