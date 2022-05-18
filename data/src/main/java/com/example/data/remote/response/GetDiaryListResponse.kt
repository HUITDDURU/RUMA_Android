package com.example.data.remote.response

data class GetDiaryListResponse(
    val diaryList : ArrayList<DiaryResponse>
){
    data class DiaryResponse(
        val id: Int,
        val title: String,
        val feeling: String,
        val image: String,
        val content: String,
        val date: String,
        val writer: String
        )
}