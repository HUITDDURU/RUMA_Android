package com.example.huitdduru.view.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.huitdduru.R
import com.example.huitdduru.databinding.FragmentUserBinding
import com.example.huitdduru.view.base.BaseFragment

class UserFragment : BaseFragment<FragmentUserBinding>(R.layout.fragment_user) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }
}