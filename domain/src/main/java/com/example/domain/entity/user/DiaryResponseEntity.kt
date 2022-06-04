package com.example.domain.entity.user

data class DiaryResponseEntity(
    val diaryId: Int,
    val currentUserImg: String?,
    val mateImg: String?,
    val isMyTurn: String?,
    val mateName: String,
    val hoursAgo: Int?
)