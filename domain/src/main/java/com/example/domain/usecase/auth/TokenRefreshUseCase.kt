package com.example.domain.usecase.auth

import com.example.domain.entity.auth.TokenRefreshResponseEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.util.RemoteErrorEmitter
import retrofit2.Response
import javax.inject.Inject

class TokenRefreshUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend fun invoke(remoteErrorEmitter: RemoteErrorEmitter, data: String) : TokenRefreshResponseEntity =
        authRepository.tokenRefresh(remoteErrorEmitter, data)
}