package com.example.data.repository

import com.example.data.datasource.MatchDataSource
import com.example.domain.entity.match.MateResponseEntity
import com.example.data.mapper.MatchMapper.mapperToMateEntity
import com.example.domain.repository.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchDataSource: MatchDataSource
): MatchRepository {
    override suspend fun mate(header: String): MateResponseEntity =
       mapperToMateEntity(matchDataSource.mate(header))

    override suspend fun terminate(header: String) =
        matchDataSource.terminate(header)
}