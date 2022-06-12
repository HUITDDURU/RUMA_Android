package com.example.huitdduru.view.mypage

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.adapter.binding.loadImage
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.databinding.ActivityProfileEditBinding
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.util.uriToMultipart
import com.example.huitdduru.viewmodel.user.UserViewModel
import com.example.huitdduru.viewmodel.user.UserViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProfileEditActivity :
    BaseActivity<ActivityProfileEditBinding>(R.layout.activity_profile_edit) {

    private val vm: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        vm.userInfo()
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.SuccessFileUpload -> { loadImage(binding.profileIv, vm.imageUrl) }
        is Event.SuccessUserInfo -> {

        }
        else -> {}
    }

    val profile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                vm.imageUpload(
                    uriToMultipart(
                        this,
                        it.data?.data!!.path!!,
                        it.data?.data!!
                    )
                )
            }
        }
}