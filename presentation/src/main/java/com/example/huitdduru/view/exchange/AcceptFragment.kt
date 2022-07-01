package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentAcceptBinding
import com.example.huitdduru.viewmodel.match.MatchViewModel

class AcceptFragment : BaseFragment<FragmentAcceptBinding>(R.layout.fragment_accept) {

    private val vm by activityViewModels<MatchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        binding.vm = vm
    }
}