package com.example.data.datasource

import com.example.data.remote.api.DiaryAPI
import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DateDiaryResponse
import com.example.data.remote.response.DiaryDetailResponse
import com.example.data.remote.response.GetDiaryListResponse
import com.example.data.remote.response.MonthDiaryResponse
import com.example.domain.base.ErrorHandler
import javax.inject.Inject

class DiaryDataSourceImpl @Inject constructor(
    private val diaryAPI: DiaryAPI,
    private val errorHandler: ErrorHandler
) : DiaryDataSource {
    override suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequest) {
        errorHandler { diaryAPI.writeDiary(header, diaryId, body) }
    }

    override suspend fun getDiaryList(header: String, diaryId: Int): GetDiaryListResponse =
        errorHandler { diaryAPI.getDiaryList(header, diaryId) }

    override suspend fun getMonthDiary(header: String, year: Int, month: Int): List<MonthDiaryResponse> =
        errorHandler { diaryAPI.getMonthDiary(header, year, month) }

    override suspend fun getDateDiary(header: String, date: String): List<DateDiaryResponse> =
        errorHandler { diaryAPI.getDateDiary(header, date) }

    override suspend fun diaryDetail(header: String, diaryId: Int): DiaryDetailResponse =
        errorHandler { diaryAPI.diaryDetail(header, diaryId) }

}