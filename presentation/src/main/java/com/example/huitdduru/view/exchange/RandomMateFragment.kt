package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentRandomMateBinding
import com.example.huitdduru.viewmodel.match.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomMateFragment : BaseFragment<FragmentRandomMateBinding>(R.layout.fragment_random_mate) {

    private val vm: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        vm.matching()
        vm.getUserInfo()
    }
}