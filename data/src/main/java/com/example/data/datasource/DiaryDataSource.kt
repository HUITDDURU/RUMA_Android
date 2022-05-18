package com.example.data.datasource

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DiaryTimeLineResponse
import com.example.data.remote.response.GetDiaryListResponse

interface DiaryDataSource {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequest)
    suspend fun getDiaryList(header: String, diaryId: Int) : GetDiaryListResponse
    suspend fun diaryTimeLine(header: String) : DiaryTimeLineResponse
}