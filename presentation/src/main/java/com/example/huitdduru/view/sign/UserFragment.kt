package com.example.huitdduru.view.sign

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.huitdduru.R
import com.example.huitdduru.databinding.FragmentUserBinding
import com.example.huitdduru.util.GalleryUtil
import com.example.huitdduru.view.base.BaseFragment

class UserFragment : BaseFragment<FragmentUserBinding>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
    }

    private val profile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                binding.profileIv.clipToOutline = true
                Glide.with(requireActivity())
                    .load(it.data?.data).into(binding.profileIv)}
        }

    fun openGallery(){
        GalleryUtil(requireActivity()).openGallery(profile)
    }
}