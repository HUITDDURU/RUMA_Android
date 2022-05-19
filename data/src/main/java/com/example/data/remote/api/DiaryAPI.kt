package com.example.data.remote.api

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DiaryTimeLineResponse
import com.example.data.remote.response.GetDiaryListResponse
import retrofit2.http.*

interface DiaryAPI {

    @POST("/diary/{diaryId}")
    suspend fun writeDiary(
        @Header("Authorization") header: String,
        @Path("diaryId") diaryId: Int,
        @Body body: WriteDiaryRequest
    )

    @GET("/diary/{diaryId}/list")
    suspend fun getDiaryList(
        @Header("Authorization") header: String,
        @Path("diaryId") diaryId: Int
    ) : GetDiaryListResponse

    @GET("/diary/chronology")
    suspend fun diaryTimeLine(
        @Header("Authorization") header: String
    ) : List<DiaryTimeLineResponse>
}