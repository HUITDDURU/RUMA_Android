package com.example.data.repository

import com.example.data.datasource.MatchDataSource
import com.example.data.remote.response.MateResponse
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchDataSource: MatchDataSource
): MatchDataSource {
    override suspend fun mate(header: String): MateResponse =
        matchDataSource.mate(header)

    override suspend fun terminate(header: String) =
        matchDataSource.terminate(header)
}