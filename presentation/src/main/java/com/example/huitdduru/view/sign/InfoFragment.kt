package com.example.huitdduru.view.sign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.huitdduru.R
import com.example.huitdduru.databinding.FragmentInfoBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.viewmodel.register.RegisterViewModel
import com.example.huitdduru.viewmodel.register.RegisterViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private val vm by activityViewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    fun sendCode() {
        if(binding.emailEt.text.toString() == "" ||
            binding.passwordEt.text.toString() == "" ||
            binding.confirmEt.text.toString() == ""){
            showToast("정보를 입력해주세요.", ToastType.INFO)
        } else {
            passwordConfirm()
        }
    }

    private fun passwordConfirm() {
        if(binding.passwordEt.text.toString() !=
                binding.confirmEt.text.toString()){
            showToast("비밀번호가 일치하지 않습니다.", ToastType.ERROR)
        } else if(binding.passwordEt.text.toString().length < 8 ||
                binding.passwordEt.text.toString().length > 16){
            showToast("비밀번호는 8~16자만 가능합니다.", ToastType.INFO)
        } else {
            vm.sendCode(binding.emailEt.text.toString().trim())
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessEmailSend -> {
            showToast("인증코드가 발송되었습니다!", ToastType.SUCCESS)
            vm.email = binding.emailEt.text.toString().trim()
            vm.password = binding.passwordEt.text.toString().trim()
            (activity as RegisterActivity).replace(CertifyFragment())
        }
        is Event.ErrorMessage -> {
            showToast(event.errorMessage, ToastType.ERROR)
        }
        else -> {}
    }
}