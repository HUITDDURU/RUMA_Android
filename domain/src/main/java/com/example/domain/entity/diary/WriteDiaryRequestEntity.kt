package com.example.domain.entity.diary

import java.time.LocalDateTime

data class WriteDiaryRequestEntity(
    val title: String,
    val feeling: String,
    val date: LocalDateTime,
    val contents: String,
    val imageUrl: String?
)
