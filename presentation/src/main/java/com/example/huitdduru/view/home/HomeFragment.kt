package com.example.huitdduru.view.home

import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = requireActivity().window
            window.statusBarColor = requireContext().getColor(R.color.primary)
        }
    }
}