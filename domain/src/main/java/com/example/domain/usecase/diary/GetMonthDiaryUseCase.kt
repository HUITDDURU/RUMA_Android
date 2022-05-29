package com.example.domain.usecase.diary

import com.example.domain.entity.diary.MonthDiaryResponseEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class GetMonthDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
){
    suspend fun invoke(header: String, year: Int, month: Int): List<MonthDiaryResponseEntity> =
        diaryRepository.getMonthDiary(header, year, month)
}