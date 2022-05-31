package com.example.huitdduru.view.write

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.huitdduru.R
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentWriteBinding
import com.example.huitdduru.util.repeatOnStarted
import com.example.huitdduru.viewmodel.diary.DiaryViewModel
import com.example.huitdduru.viewmodel.diary.DiaryViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WriteFragment : BaseFragment<FragmentWriteBinding>(R.layout.fragment_write) {

    private val feeling: ArrayList<String> = ArrayList()
    private val vm : DiaryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect{ event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.SuccessWrite -> {}
        else -> {}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun datePicker(){
        val calendar = Calendar.getInstance()
        val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.dateEt.setText("${year}-${month+1}-${dayOfMonth}")
        }
        DatePickerDialog(requireContext(),
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun emojiChecked(v: ToggleButton) {
        when(v.isChecked){
            true -> feeling.add(v.text.toString())
            false -> feeling.remove(v.text.toString())
        }
    }
}