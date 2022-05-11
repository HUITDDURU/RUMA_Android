package com.example.data.datasource.auth

import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import okhttp3.MultipartBody

interface AuthDataSource {
    suspend fun login(body: LoginRequest) : LoginResponse
    suspend fun register(part: RegisterRequest, image: MultipartBody.Part)
    suspend fun sendCode(body: SendCodeRequest)
    suspend fun certify(body: CertifyRequest)
    suspend fun tokenRefresh(header: String) : TokenRefreshResponse
}