package com.example.domain.repository

import com.example.domain.entity.auth.*
import retrofit2.Response

interface AuthRepository {
    suspend fun login(body: LoginRequest): Response<LoginResponse>
    suspend fun register(part: RegisterRequest): Response<Unit>
    suspend fun sendCode(body: SendCodeRequest): Response<Unit>
    suspend fun certify(body: CertifyRequest): Response<Unit>
    suspend fun tokenRefresh(header: String): Response<TokenRefreshResponse>
}