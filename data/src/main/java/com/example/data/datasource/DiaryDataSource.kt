package com.example.data.datasource

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DateDiaryResponse
import com.example.data.remote.response.GetDiaryListResponse
import com.example.data.remote.response.MonthDiaryResponse

interface DiaryDataSource {
    suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequest)
    suspend fun getDiaryList(header: String, diaryId: Int) : GetDiaryListResponse
    suspend fun getMonthDiary(header: String, year: Int, month: Int) : List<MonthDiaryResponse>
    suspend fun getDateDiary(header: String, date: String) : List<DateDiaryResponse>
}