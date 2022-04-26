package com.example.domain.usecase.auth

import com.example.domain.entity.auth.CertifyRequestEntity
import com.example.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class CertifyUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: CertifyRequestEntity) : Response<Unit> =
        authRepository.certify(data)
}