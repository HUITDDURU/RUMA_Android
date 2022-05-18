package com.example.domain.entity.diary

data class DiaryTimeLineResponseEntity(
    val Id: Int,
    val diaryTimeLineList: List<TimeLineDiaryEntity>,
    val isMine: Boolean
){
    data class TimeLineDiaryEntity(
        val id: Int,
        val title: String,
        val date: String,
        val writer: String
    )
}