package com.example.huitdduru.view.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentMyPageBinding
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.user.UserViewModel
import com.example.huitdduru.viewmodel.user.UserViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val vm: UserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        vm.userInfo()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event){
        is Event.SuccessUserInfo -> { binding.userInfo = event.userInfo }
        else -> {}
    }

    fun moveToEdit() {
        startActivity(Intent(requireActivity(), ProfileEditActivity::class.java))
    }
}