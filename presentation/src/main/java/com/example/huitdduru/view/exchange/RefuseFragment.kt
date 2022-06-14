package com.example.huitdduru.view.exchange

import android.os.Bundle
import android.view.View
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentRefuseBinding

class RefuseFragment : BaseFragment<FragmentRefuseBinding>(R.layout.fragment_refuse) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }
}