package com.example.domain.repository

import com.example.domain.entity.diary.DiaryTimeLineResponseEntity
import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity

interface DiaryRepository {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequestEntity)
    suspend fun getDiaryList(header: String, diaryId: Int): GetDiaryListResponseEntity
    suspend fun diaryTimeLine(header: String): DiaryTimeLineResponseEntity
}