package com.example.domain.entity.diary

data class DiaryDetailResponseEntity(
    val id: Int,
    val title: String,
    val feeling: String,
    val date: String,
    val content: String,
    val imageUrl: String?,
    val writer: String
)
