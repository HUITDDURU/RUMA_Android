package com.example.domain.usecase.auth

import com.example.domain.entity.auth.LoginRequestEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: LoginRequestEntity) =
        authRepository.login(data)
}