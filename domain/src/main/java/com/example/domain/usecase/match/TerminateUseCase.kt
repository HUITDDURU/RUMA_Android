package com.example.domain.usecase.match

import com.example.domain.repository.MatchRepository
import javax.inject.Inject

class TerminateUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    suspend fun invoke(header: String) = matchRepository.terminate(header)
}