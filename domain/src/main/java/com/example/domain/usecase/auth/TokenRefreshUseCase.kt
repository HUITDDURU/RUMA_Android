package com.example.domain.usecase.auth

import com.example.domain.entity.auth.TokenRefreshResponseEntity
import com.example.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class TokenRefreshUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend fun invoke(data: String) : Response<TokenRefreshResponseEntity> =
        authRepository.tokenRefresh(data)
}