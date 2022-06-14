package com.example.data.remote.api

import com.example.data.remote.request.EditRequest
import com.example.data.remote.response.DiaryResponse
import com.example.data.remote.response.UserInfoResponse
import retrofit2.http.*

interface UserAPI {
    @PUT("/mypage")
    suspend fun edit(
        @Header("Authorization") header: String,
        @Body body: EditRequest
    )

    @DELETE("/unregister")
    suspend fun resign(
        @Header("Authorization") header: String
    )

    @GET("/diary-list")
    suspend fun diaryList(
        @Header("Authorization") header: String
    ): List<DiaryResponse>

    @GET("/my-info")
    suspend fun userInfo(
        @Header("Authorization") header: String
    ): UserInfoResponse

    @GET("/code")
    suspend fun code(
        @Header("Authorization") header: String
    ): HashMap<String, String>
}