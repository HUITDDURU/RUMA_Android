package com.example.huitdduru.view.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseActivity
import com.example.huitdduru.databinding.ActivityDiaryDetailBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.diary.DiaryViewModel
import com.example.huitdduru.viewmodel.diary.DiaryViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DiaryDetailActivity : BaseActivity<ActivityDiaryDetailBinding>(R.layout.activity_diary_detail) {

    private val vm : DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.contentTv.movementMethod = ScrollingMovementMethod()

        intent.getIntExtra("diaryId", 0).let {
            vm.diaryDetail(it)
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessDiaryDetail -> {
            binding.data = event.diaryDetail
        }
        is Event.ErrorMessage -> {
            showToast(event.errorMessage, ToastType.ERROR)
        }
        else -> {}
    }
}