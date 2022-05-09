package com.example.data.repository

import com.example.data.datasource.auth.AuthDataSource
import com.example.data.mapper.AuthMapper.mapperToCertify
import com.example.data.mapper.AuthMapper.mapperToCode
import com.example.data.mapper.AuthMapper.mapperToLogin
import com.example.data.mapper.AuthMapper.mapperToLoginEntity
import com.example.data.mapper.AuthMapper.mapperToRefreshEntity
import com.example.data.mapper.AuthMapper.mapperToRegister
import com.example.domain.entity.auth.*
import com.example.domain.repository.AuthRepository
import com.example.domain.util.RemoteErrorEmitter
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun login(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: LoginRequestEntity
    ): LoginResponseEntity =
        mapperToLoginEntity(authDataSource.login(remoteErrorEmitter, mapperToLogin(body)))

    override suspend fun register(
        remoteErrorEmitter: RemoteErrorEmitter,
        part: RegisterRequestEntity
    ) {
        authDataSource.register(remoteErrorEmitter, mapperToRegister(part))
    }

    override suspend fun sendCode(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: SendCodeRequestEntity
    ) {
        authDataSource.sendCode(remoteErrorEmitter, mapperToCode(body))
    }

    override suspend fun certify(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: CertifyRequestEntity
    ) {
        authDataSource.certify(remoteErrorEmitter, mapperToCertify(body))
    }

    override suspend fun tokenRefresh(
        remoteErrorEmitter: RemoteErrorEmitter,
        header: String
    ): TokenRefreshResponseEntity =
        mapperToRefreshEntity(authDataSource.tokenRefresh(remoteErrorEmitter, header))
}