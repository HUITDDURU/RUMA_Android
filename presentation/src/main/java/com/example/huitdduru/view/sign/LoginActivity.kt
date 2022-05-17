package com.example.huitdduru.view.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.databinding.ActivityLoginBinding
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.view.main.MainActivity
import com.example.huitdduru.viewmodel.login.LoginViewModel
import com.example.huitdduru.viewmodel.login.LoginViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val vm : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handlerEvent(event) }
        }
    }

    fun login() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()
        if(email == "" || password == ""){
            showToast("이메일 및 패스워드를 입력해주세요.", ToastType.INFO)
        } else {
            vm.login(email, password)
        }
    }

    fun moveToRegister() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun handlerEvent(event: Event) = when(event){
        is Event.SuccessLogin -> {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        is Event.ErrorMessage -> { showToast(event.errorMessage, ToastType.ERROR) }
    }
}