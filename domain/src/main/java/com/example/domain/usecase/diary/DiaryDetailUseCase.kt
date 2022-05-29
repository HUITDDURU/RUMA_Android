package com.example.domain.usecase.diary

import com.example.domain.entity.diary.DiaryDetailResponseEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryDetailUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend fun invoke(header: String, diaryId: Int): DiaryDetailResponseEntity =
        diaryRepository.diaryDetail(header, diaryId)
}