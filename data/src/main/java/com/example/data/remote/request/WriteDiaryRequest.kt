package com.example.data.remote.request

import java.time.LocalDateTime

data class WriteDiaryRequest(
    val title: String,
    val feeling: String,
    val date: LocalDateTime,
    val contents: String,
    val imageUrl: String?
)
