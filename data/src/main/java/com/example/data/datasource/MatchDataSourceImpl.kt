package com.example.data.datasource

import com.example.data.remote.api.MatchAPI
import com.example.data.remote.response.MateResponse
import com.example.domain.base.ErrorHandler
import javax.inject.Inject

class MatchDataSourceImpl @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val matchAPI: MatchAPI
): MatchDataSource {
    override suspend fun mate(header: String): MateResponse =
        errorHandler { matchAPI.mate(header) }

    override suspend fun terminate(header: String) =
        errorHandler { matchAPI.terminate(header) }
}