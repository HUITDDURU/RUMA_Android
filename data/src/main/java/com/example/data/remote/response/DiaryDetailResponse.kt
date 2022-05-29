package com.example.data.remote.response

data class DiaryDetailResponse(
    val id: Int,
    val title: String,
    val feeling: String,
    val date: String,
    val content: String,
    val image: String?,
    val writer: String
)
