package com.example.domain.usecase.auth

import com.example.domain.entity.auth.RegisterRequestEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {
    suspend fun invoke(body: RegisterRequestEntity) =
        authRepository.register(body)
}