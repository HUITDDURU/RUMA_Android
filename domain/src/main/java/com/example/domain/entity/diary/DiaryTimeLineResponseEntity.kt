package com.example.domain.entity.diary

data class DiaryTimeLineResponseEntity(
    val Id: Int,
    val diaryTimeLineList: ArrayList<TimeLineDiary>,
    val isMine: Boolean
){
    data class TimeLineDiary(
        val id: Int,
        val title: String,
        val date: String,
        val writer: String
    )
}