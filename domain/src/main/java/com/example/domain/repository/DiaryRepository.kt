package com.example.domain.repository

import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.entity.diary.MonthDiaryResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity

interface DiaryRepository {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequestEntity)
    suspend fun getDiaryList(header: String, diaryId: Int): GetDiaryListResponseEntity
    suspend fun getMonthDiary(header: String, year: Int, month: Int): List<MonthDiaryResponseEntity>
}