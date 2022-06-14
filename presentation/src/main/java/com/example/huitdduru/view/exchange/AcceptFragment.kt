package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentAcceptBinding

class AcceptFragment : BaseFragment<FragmentAcceptBinding>(R.layout.fragment_accept) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }
}