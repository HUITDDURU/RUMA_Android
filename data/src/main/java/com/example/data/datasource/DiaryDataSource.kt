package com.example.data.datasource

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DiaryResponse
import com.example.data.remote.response.DiaryTimeLineResponse

interface DiaryDataSource {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequest)
    suspend fun getDiaryList(header: String, diaryId: Int) : List<DiaryResponse>
    suspend fun diaryTimeLine(header: String) : DiaryTimeLineResponse
}