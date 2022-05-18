package com.example.data.mapper

import com.example.data.remote.request.WriteDiaryRequest
import com.example.data.remote.response.DiaryTimeLineResponse
import com.example.data.remote.response.DiaryTimeLineResponse.TimeLineDiary
import com.example.data.remote.response.GetDiaryListResponse
import com.example.data.remote.response.GetDiaryListResponse.DiaryResponse
import com.example.domain.entity.diary.DiaryTimeLineResponseEntity
import com.example.domain.entity.diary.DiaryTimeLineResponseEntity.TimeLineDiaryEntity
import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.entity.diary.GetDiaryListResponseEntity.DiaryResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity

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

    fun mapperToDiaryTimeLine(diaryTimeLineResponseEntity: DiaryTimeLineResponse): DiaryTimeLineResponseEntity =
        diaryTimeLineResponseEntity.run {
            DiaryTimeLineResponseEntity(
                Id,
                mapperToTimeLine(diaryTimeLineResponseEntity.diaryTimeLineList),
                isMine
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

    private fun mapperToTimeLine(timeLineDiaryEntity: List<TimeLineDiary>):
            List<TimeLineDiaryEntity> = timeLineDiaryEntity.toList().map {
                TimeLineDiaryEntity(
                    it.id,
                    it.title,
                    it.date,
                    it.writer
                )
    }
}