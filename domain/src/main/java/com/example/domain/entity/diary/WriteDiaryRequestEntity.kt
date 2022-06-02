package com.example.domain.entity.diary

data class WriteDiaryRequestEntity(
    val title: String,
    val feeling: String,
    val date: String,
    val contents: String,
    val imageUrl: String?
)
