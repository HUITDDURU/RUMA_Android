package com.example.domain.usecase.diary

import com.example.domain.entity.diary.DateDiaryResponseEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class GetDateDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend fun invoke(header: String, date: String) : List<DateDiaryResponseEntity> =
        diaryRepository.getDateDiary(header, date)
}