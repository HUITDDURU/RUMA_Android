package com.example.data.remote.request

data class WriteDiaryRequest(
    val title: String,
    val feeling: String,
    val date: String,
    val contents: String,
    val imageUrl: String?
)
