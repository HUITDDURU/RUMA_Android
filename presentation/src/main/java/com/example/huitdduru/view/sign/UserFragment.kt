package com.example.huitdduru.view.sign

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.huitdduru.R
import com.example.huitdduru.databinding.FragmentUserBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.util.uriToMultipart
import com.example.huitdduru.viewmodel.register.RegisterViewModel
import com.example.huitdduru.viewmodel.register.RegisterViewModel.Event
import kotlinx.coroutines.flow.collect

class UserFragment : BaseFragment<FragmentUserBinding>(R.layout.fragment_user) {

    private val vm by activityViewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessRegister -> {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        is Event.SuccessFileUpload -> {
            binding.profileIv.clipToOutline = true
            Glide.with(requireActivity())
                .load(Uri.parse(vm.file))
                .into(binding.profileIv)
        }
        else -> {}
    }

    val profile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                vm.fileUpload(uriToMultipart(
                    requireContext(),
                    it.data?.data!!.path!!,
                    it.data?.data!!
                ))
            }
        }

    fun register() {
        if(binding.nicknameEt.text.toString() == ""){
            showToast("???????????? ??????????????????!", ToastType.INFO)
        } else {
            vm.name = binding.nicknameEt.text.toString().trim()
            vm.intro = binding.descriptionEt.text.toString().trim()
            vm.register()
        }
    }
}