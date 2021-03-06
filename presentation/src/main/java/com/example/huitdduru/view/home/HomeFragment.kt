package com.example.huitdduru.view.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.domain.entity.diary.MonthDiaryResponseEntity
import com.example.huitdduru.R
import com.example.huitdduru.adapter.DiaryRecyclerViewAdapter
import com.example.huitdduru.base.BaseFragment
import com.example.huitdduru.databinding.FragmentHomeBinding
import com.example.huitdduru.util.*
import com.example.huitdduru.viewmodel.diary.DiaryViewModel
import com.example.huitdduru.viewmodel.diary.DiaryViewModel.Event
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), DiaryRecyclerViewAdapter.OnItemClickListener {

    private val vm by activityViewModels<DiaryViewModel>()
    private lateinit var adapter :DiaryRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
        adapter = DiaryRecyclerViewAdapter(this)

        vm.getMonthDiary(getYear().toInt(), getMonth().toInt())
        vm.getDateDiary(getDate())

        binding.calendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onClickListener() {
            }

            override fun onDataUpdate() {
            }

            override fun onDayChanged() {
            }

            override fun onDaySelect() {
                val year = binding.calendar.selectedDay!!.year
                val month = binding.calendar.selectedDay!!.month
                val day = binding.calendar.selectedDay!!.day
                val date = getDate("${year}-${month + 1}-${day}")

                binding.yearTv.text = year.toString()
                binding.dateTv.text = "${month + 1}??? ${day}??? ??????"
                vm.getDateDiary(date)
            }

            override fun onItemClick(v: View) {
            }

            override fun onMonthChange() {
                val year = binding.calendar.year
                val month = binding.calendar.month+1
                vm.getMonthDiary(year, month)
            }

            override fun onWeekChange(position: Int) {
            }

        })

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.diaryRv)
        binding.diaryRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.diaryRv.adapter = adapter
    }

    override fun onItemClick(diaryId: Int) {
        val intent = Intent(requireContext(), DiaryDetailActivity::class.java)
        intent.putExtra("diaryId", diaryId)
        startActivity(intent)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.SuccessTimeLine -> {
            setEventTag(event.monthDiary)
        }
        is Event.SuccessGetDateDiary -> {
            adapter.submitList(event.dateDiary)
        }
        else -> {
        }
    }

    private fun setEventTag(timeLine: List<MonthDiaryResponseEntity>) {
        timeLine.forEach {
            if (it.count > 0) {
                val date = it.date.split("-")
                binding.calendar.addEventTag(
                    date[0].toInt(),
                    date[1].toInt() - 1,
                    date[2].toInt(),
                    ContextCompat.getColor(requireContext(), R.color.primary)
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun bindDate(): String {
        var month = getMonth()
        if (month[0].toString().equals("0"))
            month = month.removeRange(0, 1)
        return "${month}??? ${getDay()}??? ??????"
    }
}