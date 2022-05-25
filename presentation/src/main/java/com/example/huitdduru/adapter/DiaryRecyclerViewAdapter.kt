package com.example.huitdduru.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.diary.DateDiaryResponseEntity
import com.example.domain.entity.diary.MonthDiaryResponseEntity
import com.example.huitdduru.R
import com.example.huitdduru.databinding.HomeDiaryItemBinding

class DiaryRecyclerViewAdapter(private val onClick: () -> Unit) :
    ListAdapter<DateDiaryResponseEntity, DiaryRecyclerViewAdapter.ViewHolder>(DiaryDiffUtil) {

    class ViewHolder(private val binding: HomeDiaryItemBinding, private val onClick: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private var dateDiaryResponseEntity: DateDiaryResponseEntity? = null

        fun bind(dateDiaryResponseEntity: DateDiaryResponseEntity) {
            binding.diary = dateDiaryResponseEntity
            this.dateDiaryResponseEntity = dateDiaryResponseEntity
            binding.root.setOnClickListener{ onClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<HomeDiaryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.home_diary_item,
            parent,
            false
        )
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiaryDiffUtil : DiffUtil.ItemCallback<DateDiaryResponseEntity>() {
        override fun areItemsTheSame(
            oldItem: DateDiaryResponseEntity,
            newItem: DateDiaryResponseEntity
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DateDiaryResponseEntity,
            newItem: DateDiaryResponseEntity
        ): Boolean = oldItem.id == newItem.id

    }
}