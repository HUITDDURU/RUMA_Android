package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentFindMateBinding
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.match.MatchViewModel
import com.example.huitdduru.viewmodel.match.MatchViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FindMateFragment : BaseFragment<FragmentFindMateBinding>(R.layout.fragment_find_mate) {

    private val vm by activityViewModels<MatchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        binding.userInfo = vm.getUser()
        vm.cancel()
        vm.success()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event){
        else -> {}
    }
}