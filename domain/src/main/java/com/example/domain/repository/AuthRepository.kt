package com.example.domain.repository

import com.example.domain.entity.auth.*
import com.example.domain.util.RemoteErrorEmitter

interface AuthRepository {
    suspend fun login(remoteErrorEmitter: RemoteErrorEmitter, body: LoginRequestEntity): LoginResponseEntity
    suspend fun register(remoteErrorEmitter: RemoteErrorEmitter, part: RegisterRequestEntity)
    suspend fun sendCode(remoteErrorEmitter: RemoteErrorEmitter, body: SendCodeRequestEntity)
    suspend fun certify(remoteErrorEmitter: RemoteErrorEmitter, body: CertifyRequestEntity)
    suspend fun tokenRefresh(remoteErrorEmitter: RemoteErrorEmitter, header: String): TokenRefreshResponseEntity
}