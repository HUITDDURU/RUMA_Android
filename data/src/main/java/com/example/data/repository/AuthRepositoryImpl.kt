package com.example.data.repository

import com.example.data.datasource.auth.AuthDataSource
import com.example.data.datasource.auth.LocalAuthDataSource
import com.example.data.mapper.AuthMapper.mapperToCertify
import com.example.data.mapper.AuthMapper.mapperToCode
import com.example.data.mapper.AuthMapper.mapperToLogin
import com.example.data.mapper.AuthMapper.mapperToLoginEntity
import com.example.data.mapper.AuthMapper.mapperToRefreshEntity
import com.example.data.mapper.AuthMapper.mapperToRegister
import com.example.domain.entity.auth.*
import com.example.domain.repository.AuthRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) : AuthRepository {
    override suspend fun login(
        body: LoginRequestEntity
    ){
        val response = mapperToLoginEntity(authDataSource.login(mapperToLogin(body)))
        saveToken(response)
    }

    override suspend fun register(
        body: RegisterRequestEntity
    ) {
        authDataSource.register(mapperToRegister(body))
    }

    override suspend fun sendCode(
        body: SendCodeRequestEntity
    ) {
        authDataSource.sendCode(mapperToCode(body))
    }

    override suspend fun certify(
        body: CertifyRequestEntity
    ) {
        authDataSource.certify(mapperToCertify(body))
    }

    override suspend fun tokenRefresh(
        header: String
    ): TokenRefreshResponseEntity =
        mapperToRefreshEntity(authDataSource.tokenRefresh(header))

    override suspend fun fileUpload(
        file: MultipartBody.Part
    ): HashMap<String, String> =
        authDataSource.fileUpload(file)

    private suspend fun saveToken(loginResponseEntity: LoginResponseEntity){
        localAuthDataSource.apply {
            setAccessToken(loginResponseEntity.accessToken)
            setRefreshToken(loginResponseEntity.refreshToken)
        }
    }
}