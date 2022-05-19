package com.example.data.repository

import com.example.data.datasource.DiaryDataSource
import com.example.data.mapper.DiaryMapper.mapperToDiaryTimeLine
import com.example.data.mapper.DiaryMapper.mapperToGetDiaryList
import com.example.data.mapper.DiaryMapper.mapperToWriteDiaryEntity
import com.example.domain.entity.diary.DiaryTimeLineResponseEntity
import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryDataSource: DiaryDataSource
): DiaryRepository {

    override suspend fun writeDiary(header: String, diaryId: Int, body: WriteDiaryRequestEntity) {
        diaryDataSource.writeDiary(header, diaryId, mapperToWriteDiaryEntity(body))
    }

    override suspend fun getDiaryList(header: String, diaryId: Int): GetDiaryListResponseEntity =
        mapperToGetDiaryList(diaryDataSource.getDiaryList(header, diaryId))

    override suspend fun diaryTimeLine(header: String): List<DiaryTimeLineResponseEntity> =
        mapperToDiaryTimeLine(diaryDataSource.diaryTimeLine(header))
}