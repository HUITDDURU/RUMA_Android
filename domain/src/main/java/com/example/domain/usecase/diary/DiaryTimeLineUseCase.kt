package com.example.domain.usecase.diary

import com.example.domain.entity.diary.DiaryTimeLineResponseEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryTimeLineUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
){
    suspend fun invoke(header: String): DiaryTimeLineResponseEntity =
        diaryRepository.diaryTimeLine(header)
}