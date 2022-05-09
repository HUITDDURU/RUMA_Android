package com.example.domain.usecase.auth

import com.example.domain.entity.auth.CertifyRequestEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.util.RemoteErrorEmitter
import retrofit2.Response
import javax.inject.Inject

class CertifyUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(remoteErrorEmitter: RemoteErrorEmitter, data: CertifyRequestEntity) =
        authRepository.certify(remoteErrorEmitter, data)
}