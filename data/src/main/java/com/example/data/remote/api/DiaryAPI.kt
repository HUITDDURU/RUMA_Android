package com.example.data.remote.api

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.GetDiaryListResponse
import com.example.data.remote.response.MonthDiaryResponse
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

    @GET("/calendar/{year}/{month}")
    suspend fun getMonthDiary(
        @Header("Authorization") header: String,
        @Path("year") year: Int,
        @Path("month") month: Int
    ) : List<MonthDiaryResponse>
}