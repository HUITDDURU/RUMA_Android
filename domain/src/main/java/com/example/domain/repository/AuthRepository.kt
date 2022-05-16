package com.example.domain.repository

import com.example.domain.entity.auth.*
import okhttp3.MultipartBody

interface AuthRepository {
    suspend fun login(body: LoginRequestEntity)
    suspend fun register(body: RegisterRequestEntity)
    suspend fun sendCode(body: SendCodeRequestEntity)
    suspend fun certify(body: CertifyRequestEntity)
    suspend fun tokenRefresh(header: String): TokenRefreshResponseEntity
    suspend fun fileUpload(file: MultipartBody.Part): HashMap<String, String>
}