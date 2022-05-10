package com.example.huitdduru.view.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.databinding.FragmentCertifyBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.view.base.BaseFragment
import com.example.huitdduru.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CertifyFragment : BaseFragment<FragmentCertifyBinding>(R.layout.fragment_certify) {

    private val vm by activityViewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    fun certify(){
        if(binding.codeEt.text.toString() == ""){
            showToast("인증코드를 입력해주세요.", ToastType.INFO)
        } else {
            vm.certify(binding.codeEt.text.toString())
        }
    }

    private fun handleEvent(event: RegisterViewModel.Event) = when(event) {
        is RegisterViewModel.Event.SuccessEmailCertify -> {
            showToast("인증되었습니다!", ToastType.SUCCESS)
            (activity as RegisterActivity).replace(UserFragment())
        }
        else -> {}
    }
}