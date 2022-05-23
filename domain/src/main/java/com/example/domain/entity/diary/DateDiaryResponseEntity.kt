package com.example.domain.entity.diary

data class DateDiaryResponseEntity(
    val id: Int,
    val title: String,
    val date: String,
    val writer: String,
    val imageUrl: String?,
    val isMine: Boolean
)