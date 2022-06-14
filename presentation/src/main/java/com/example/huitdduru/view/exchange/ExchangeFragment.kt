package com.example.huitdduru.view.exchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentExchangeBinding

class ExchangeFragment : BaseFragment<FragmentExchangeBinding>(R.layout.fragment_exchange) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }

    fun moveToMatch(type: Int) {
        val intent = Intent(requireActivity(), MatchActivity::class.java)
            .apply { putExtra("type", type) }
        startActivity(intent)
    }
}