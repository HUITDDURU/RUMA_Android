package com.example.data.repository

import com.example.data.datasource.DiaryDataSource
import com.example.data.mapper.DiaryMapper.mapperToDateDiary
import com.example.data.mapper.DiaryMapper.mapperToDiaryDetail
import com.example.data.mapper.DiaryMapper.mapperToMonthDiary
import com.example.data.mapper.DiaryMapper.mapperToGetDiaryList
import com.example.data.mapper.DiaryMapper.mapperToWriteDiaryEntity
import com.example.domain.entity.diary.*
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

    override suspend fun getMonthDiary(header: String, year: Int, month: Int): List<MonthDiaryResponseEntity> =
        mapperToMonthDiary(diaryDataSource.getMonthDiary(header, year, month))

    override suspend fun getDateDiary(header: String, date: String): List<DateDiaryResponseEntity> =
        mapperToDateDiary(diaryDataSource.getDateDiary(header, date))

    override suspend fun diaryDetail(header: String, diaryId: Int): DiaryDetailResponseEntity =
        mapperToDiaryDetail(diaryDataSource.diaryDetail(header, diaryId))
}