package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentLocalMateBinding
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.user.UserViewModel
import com.example.huitdduru.viewmodel.user.UserViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LocalMateFragment : BaseFragment<FragmentLocalMateBinding>(R.layout.fragment_local_mate) {

    private val vm: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        vm.code()
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessCode -> binding.codeTv.text = event.code
        else -> {}
    }
}