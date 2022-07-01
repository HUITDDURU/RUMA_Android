package com.example.data.datasource

import com.example.data.remote.response.MateResponse

interface MatchDataSource {
    suspend fun mate(header: String): MateResponse
    suspend fun terminate(header: String)
}