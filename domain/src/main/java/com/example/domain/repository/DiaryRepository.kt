package com.example.domain.repository

import com.example.domain.entity.diary.*

interface DiaryRepository {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequestEntity)
    suspend fun getDiaryList(header: String, diaryId: Int): GetDiaryListResponseEntity
    suspend fun getMonthDiary(header: String, year: Int, month: Int): List<MonthDiaryResponseEntity>
    suspend fun getDateDiary(header: String, date: String): List<DateDiaryResponseEntity>
    suspend fun diaryDetail(header: String, diaryId: Int) : DiaryDetailResponseEntity
}