package com.example.data.remote.api

import com.example.data.remote.request.*
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface AuthAPI {

    @POST("/auth")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @POST("/register")
    suspend fun register(
        @Part registerRequest: RegisterRequest
    ): Response<Unit>

    @POST("/email")
    suspend fun sendCode(
        @Body email: SendCodeRequest
    ): Response<Unit>

    @PUT("/email")
    suspend fun certify(
        @Body body: CertifyRequest
    ): Response<Unit>

    @PUT("/auth")
    suspend fun tokenRefresh(
        @Body refresh_token: TokenRefreshRequest
    ): Response<TokenRefreshResponse>
    
}