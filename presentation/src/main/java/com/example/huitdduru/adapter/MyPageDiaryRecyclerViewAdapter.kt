package com.example.huitdduru.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.user.DiaryResponseEntity
import com.example.huitdduru.R
import com.example.huitdduru.adapter.MyPageDiaryRecyclerViewAdapter.*
import com.example.huitdduru.databinding.MypageDiaryItemBinding

class MyPageDiaryRecyclerViewAdapter(private val listener: OnItemClickListener) :
    ListAdapter<DiaryResponseEntity, ViewHolder>(DiaryDiffUtil) {

    interface OnItemClickListener {
        fun onItemClick(diaryId: Int)
    }

    inner class ViewHolder(private val binding: MypageDiaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diaryResponseEntity: DiaryResponseEntity) {
            binding.diary = diaryResponseEntity

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                val item = getItem(position)
                binding.root.setOnClickListener { listener.onItemClick(item.diaryId) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MypageDiaryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.mypage_diary_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiaryDiffUtil : DiffUtil.ItemCallback<DiaryResponseEntity>() {
        override fun areItemsTheSame(
            oldItem: DiaryResponseEntity,
            newItem: DiaryResponseEntity
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DiaryResponseEntity,
            newItem: DiaryResponseEntity
        ): Boolean = oldItem.diaryId == newItem.diaryId

    }
}