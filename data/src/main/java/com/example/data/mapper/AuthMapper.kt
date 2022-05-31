package com.example.data.mapper

import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import com.example.domain.entity.auth.*

object AuthMapper {
    fun mapperToLoginEntity(loginResponse: LoginResponse) : LoginResponseEntity =
        loginResponse.run { LoginResponseEntity(accessToken, refreshToken, diaryId) }

    fun mapperToLogin(loginRequestEntity: LoginRequestEntity) : LoginRequest =
        loginRequestEntity.run { LoginRequest(email, password) }

    fun mapperToRegister(registerRequestEntity: RegisterRequestEntity) : RegisterRequest =
        registerRequestEntity.run { RegisterRequest(name, email, password, intro, imageUrl) }

    fun mapperToCode(sendCodeRequestEntity: SendCodeRequestEntity) : SendCodeRequest =
        sendCodeRequestEntity.run { SendCodeRequest(email) }

    fun mapperToCertify(certifyRequestEntity: CertifyRequestEntity) : CertifyRequest =
        certifyRequestEntity.run { CertifyRequest(email, code) }

    fun mapperToRefreshEntity(tokenRefreshResponse: TokenRefreshResponse) : TokenRefreshResponseEntity =
        tokenRefreshResponse.run { TokenRefreshResponseEntity(accessToken, refreshToken) }
}