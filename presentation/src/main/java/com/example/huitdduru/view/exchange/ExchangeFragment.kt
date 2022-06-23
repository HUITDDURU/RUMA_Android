package com.example.huitdduru.view.exchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentExchangeBinding
import com.example.huitdduru.viewmodel.match.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeFragment : BaseFragment<FragmentExchangeBinding>(R.layout.fragment_exchange) {

    private val vm: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        vm.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.disconnect()
    }

    fun moveToMatch(type: Int) {
        val intent = Intent(requireActivity(), MatchActivity::class.java)
            .apply { putExtra("type", type) }
        startActivity(intent)
    }
}