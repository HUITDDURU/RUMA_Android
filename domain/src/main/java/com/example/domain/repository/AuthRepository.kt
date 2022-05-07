package com.example.domain.repository

import com.example.domain.entity.auth.*
import com.example.domain.util.RemoteErrorEmitter
import retrofit2.Response

interface AuthRepository {
    suspend fun login(remoteErrorEmitter: RemoteErrorEmitter, body: LoginRequestEntity): Response<LoginResponseEntity>
    suspend fun register(remoteErrorEmitter: RemoteErrorEmitter, part: RegisterRequestEntity): Response<Unit>
    suspend fun sendCode(remoteErrorEmitter: RemoteErrorEmitter, body: SendCodeRequestEntity): Response<Unit>
    suspend fun certify(remoteErrorEmitter: RemoteErrorEmitter, body: CertifyRequestEntity): Response<Unit>
    suspend fun tokenRefresh(remoteErrorEmitter: RemoteErrorEmitter, header: String): Response<TokenRefreshResponseEntity>
}