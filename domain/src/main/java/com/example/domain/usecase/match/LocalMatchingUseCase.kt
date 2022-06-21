package com.example.domain.usecase.match

import com.example.domain.repository.SocketRepository
import javax.inject.Inject

class LocalMatchingUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) {
    suspend fun invoke(code: String) = socketRepository.localMatching(code)
}