package com.example.data.datasource

import com.example.data.remote.api.DiaryAPI
import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DiaryTimeLineResponse
import com.example.data.remote.response.GetDiaryListResponse
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

    override suspend fun diaryTimeLine(header: String): DiaryTimeLineResponse =
        errorHandler { diaryAPI.diaryTimeLine(header) }
}