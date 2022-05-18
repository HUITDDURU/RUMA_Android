package com.example.domain.repository

import com.example.domain.entity.diary.DiaryResponseEntity
import com.example.domain.entity.diary.DiaryTimeLineResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity

interface DiaryRepository {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequestEntity)
    suspend fun getDiaryList(header: String, diaryId: Int): List<DiaryResponseEntity>
    suspend fun diaryTimeLine(header: String): DiaryTimeLineResponseEntity
}