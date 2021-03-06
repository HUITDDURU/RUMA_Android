package com.example.data.remote.api

import com.example.data.remote.request.*
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthAPI {

    @POST("/auth")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    )

    @POST("/email")
    suspend fun sendCode(
        @Body email: SendCodeRequest
    )

    @PUT("/email")
    suspend fun certify(
        @Body body: CertifyRequest
    )

    @PUT("/auth")
    suspend fun tokenRefresh(
        @Header("refresh-token") refresh_token: String
    ): TokenRefreshResponse

    @Multipart
    @POST("/image")
    suspend fun fileUpload(
        @Part file: MultipartBody.Part
    ): HashMap<String, String>
}