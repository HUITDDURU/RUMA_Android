package com.example.data.remote.response

data class DiaryTimeLineResponse(
    val Id: Int,
    val diaryTimeLine: TimeLineDiary,
    val isMine: Boolean
){
    data class TimeLineDiary(
        val id: Int,
        val title: String,
        val date: String,
        val writer: String
    )
}
