package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentRandomMateBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.match.MatchViewModel
import com.example.huitdduru.viewmodel.match.MatchViewModel.Event
import com.example.huitdduru.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomMateFragment : BaseFragment<FragmentRandomMateBinding>(R.layout.fragment_random_mate) {

    private val vm by activityViewModels<MatchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        vm.matching()
        vm.getUserInfo()

        lifecycleScope.launch {
            vm.sharedFlow.collect {
                showToast(it, ToastType.NORMAL)
            }
        }

        repeatOnStarted {
            vm.eventFlow.collect { event ->  handleEvent(event)}
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessGetUserInfo -> {
            (activity as MatchActivity).replace(FindMateFragment())
        }
        else -> {}
    }
}