package com.example.domain.repository

import com.example.domain.entity.auth.*

interface AuthRepository {
    suspend fun login(body: LoginRequestEntity): LoginResponseEntity
    suspend fun register(part: RegisterRequestEntity)
    suspend fun sendCode(body: SendCodeRequestEntity)
    suspend fun certify(body: CertifyRequestEntity)
    suspend fun tokenRefresh(header: String): TokenRefreshResponseEntity
}