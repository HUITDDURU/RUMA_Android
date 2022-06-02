package com.example.huitdduru.view.write

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentWriteBinding
import com.example.huitdduru.util.ToastType
import com.example.huitdduru.util.getAll
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.util.uriToMultipart
import com.example.huitdduru.view.main.MainActivity
import com.example.huitdduru.viewmodel.diary.DiaryViewModel
import com.example.huitdduru.viewmodel.diary.DiaryViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WriteFragment : BaseFragment<FragmentWriteBinding>(R.layout.fragment_write) {

    private val feeling: ArrayList<String> = ArrayList()
    private val vm: DiaryViewModel by viewModels()
    private var image: String = ""

    val profile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                vm.imageUpload(uriToMultipart(
                    requireContext(),
                    it.data?.data!!.path!!,
                    it.data?.data!!
                ))
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect{ event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessWrite -> {
            showToast("일기가 작성되었습니다!", ToastType.SUCCESS)
            (activity as MainActivity).refreshFragment()
        }
        is Event.SuccessFileUpload -> {
            image = event.imageUrl
            binding.imageAddTv.text = "이미지가 등록되었습니다!"
        }
        else -> {}
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun write() {
        val title = binding.titleEt.text.toString()
        val date = binding.dateEt.text.toString()
        val content = binding.contentEt.text.toString()
        if(title != "" && date != "" && content != "" && feeling.getAll() != ""){
            vm.writeDiary(
                title,
                feeling.getAll(),
                date,
                content,
                image
            )
        } else {
            showToast("빈칸을 채워주세요", ToastType.INFO)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun emojiChecked(v: ToggleButton) {
        when(v.isChecked){
            true -> feeling.add(v.text.toString())
            false -> feeling.remove(v.text.toString())
        }
    }
}