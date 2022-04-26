package com.example.domain.repository

import com.example.domain.entity.auth.*
import retrofit2.Response

interface AuthRepository {
    suspend fun login(body: LoginRequestEntity): Response<LoginResponseEntity>
    suspend fun register(part: RegisterRequestEntity): Response<Unit>
    suspend fun sendCode(body: SendCodeRequestEntity): Response<Unit>
    suspend fun certify(body: CertifyRequestEntity): Response<Unit>
    suspend fun tokenRefresh(header: String): Response<TokenRefreshResponseEntity>
}