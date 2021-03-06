package com.example.domain.entity.diary

data class GetDiaryListResponseEntity(
    val matchedUserName: String,
    val list: List<DiaryResponseEntity>
){
    data class DiaryResponseEntity(
        val id: Int,
        val title: String,
        val feeling: String,
        val image: String,
        val content: String,
        val date: String,
        val writer: String
    )
}


