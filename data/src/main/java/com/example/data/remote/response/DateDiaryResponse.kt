package com.example.data.remote.response

data class DateDiaryResponse(
    val id: Int,
    val title: String,
    val date: String,
    val writer: String,
    val imageUrl: String?,
    val isMine: Boolean
)