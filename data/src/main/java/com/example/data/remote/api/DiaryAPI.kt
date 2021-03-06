package com.example.data.remote.api

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DateDiaryResponse
import com.example.data.remote.response.DiaryDetailResponse
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

    @GET("/calendar/{date}")
    suspend fun getDateDiary(
        @Header("Authorization") header: String,
        @Path("date") date: String
    ) : List<DateDiaryResponse>

    @GET("diary/{diaryDetailId}")
    suspend fun diaryDetail(
        @Header("Authorization") header: String,
        @Path("diaryDetailId") diaryId: Int
    ) : DiaryDetailResponse
}