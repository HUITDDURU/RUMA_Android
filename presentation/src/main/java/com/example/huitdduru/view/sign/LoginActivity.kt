package com.example.huitdduru.view.sign

import android.content.Intent
import android.os.Bundle
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityLoginBinding
import com.example.huitdduru.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

    }

    fun moveToRegister() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
}