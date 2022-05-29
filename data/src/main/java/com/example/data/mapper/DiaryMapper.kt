package com.example.data.mapper

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DateDiaryResponse
import com.example.data.remote.response.DiaryDetailResponse
import com.example.data.remote.response.GetDiaryListResponse
import com.example.data.remote.response.GetDiaryListResponse.DiaryResponse
import com.example.data.remote.response.MonthDiaryResponse
import com.example.domain.entity.diary.*
import com.example.domain.entity.diary.GetDiaryListResponseEntity.DiaryResponseEntity

object DiaryMapper {
    fun mapperToWriteDiaryEntity(writeDiaryRequest: WriteDiaryRequestEntity): WriteDiaryRequest =
        writeDiaryRequest.run { WriteDiaryRequest(title, feeling, date, contents, imageUrl) }

    fun mapperToGetDiaryList(getDiaryListResponse: GetDiaryListResponse): GetDiaryListResponseEntity =
        getDiaryListResponse.run {
            GetDiaryListResponseEntity(
                matchedUserName,
                mapperToGetDiaryList(getDiaryListResponse.list)
            )
        }

    fun mapperToMonthDiary(monthDiaryResponse: List<MonthDiaryResponse>): List<MonthDiaryResponseEntity> =
        monthDiaryResponse.map {
            MonthDiaryResponseEntity(
                it.date,
                it.count
            )
        }

    fun mapperToDateDiary(dateDiaryResponse: List<DateDiaryResponse>): List<DateDiaryResponseEntity> =
        dateDiaryResponse.map {
            DateDiaryResponseEntity(
                it.id,
                it.title,
                it.date,
                it.writer,
                it.imageUrl,
                it.isMine
            )
        }

    private fun mapperToGetDiaryList(list: List<DiaryResponse>): List<DiaryResponseEntity> =
        list.toList().map {
            DiaryResponseEntity(
                it.id,
                it.title,
                it.feeling,
                it.image,
                it.content,
                it.date,
                it.writer
            )
        }

    fun mapperToDiaryDetail(diaryDetailResponse: DiaryDetailResponse): DiaryDetailResponseEntity =
        diaryDetailResponse.run {
            DiaryDetailResponseEntity(
                id,
                title,
                feeling,
                date,
                content,
                image,
                writer
            )
        }
}