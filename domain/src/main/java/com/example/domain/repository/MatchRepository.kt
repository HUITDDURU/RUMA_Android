package com.example.domain.repository

import com.example.domain.entity.match.MateResponseEntity

interface MatchRepository {
    suspend fun mate(header: String): MateResponseEntity
    suspend fun terminate(header: String)
}