package com.example.data.datasource

import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthDataSource {
    suspend fun login(body: LoginRequest) : LoginResponse
    suspend fun register(body: RegisterRequest)
    suspend fun sendCode(body: SendCodeRequest)
    suspend fun certify(body: CertifyRequest)
    suspend fun tokenRefresh(header: String) : TokenRefreshResponse
    suspend fun fileUpload(file: MultipartBody.Part): HashMap<String, String>
}