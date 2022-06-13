package com.example.data.remote.response

data class DiaryResponse(
    val diaryId: Int,
    val currentUserImg: String?,
    val mateImg: String?,
    val isMyTurn: String?,
    val mateName: String,
    val hoursAgo: Int?
)
