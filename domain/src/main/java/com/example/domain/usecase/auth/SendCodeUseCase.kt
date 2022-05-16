package com.example.domain.usecase.auth

import com.example.domain.entity.auth.SendCodeRequestEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: SendCodeRequestEntity) =
        authRepository.sendCode(data)
}