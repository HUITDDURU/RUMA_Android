package com.example.huitdduru.view.write

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentWriteBinding

class WriteFragment : BaseFragment<FragmentWriteBinding>(R.layout.fragment_write) {

    private val feeling: ArrayList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun emojiChecked(v: ToggleButton) {
        when(v.isChecked){
            true -> feeling.add(v.text.toString())
            false -> feeling.remove(v.text.toString())
        }
    }
}