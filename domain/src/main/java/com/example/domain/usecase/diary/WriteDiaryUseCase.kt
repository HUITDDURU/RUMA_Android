package com.example.domain.usecase.diary

import com.example.domain.entity.diary.WriteDiaryRequestEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class WriteDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend fun invoke(
        header: String,
        diaryId: Int,
        body: WriteDiaryRequestEntity
    ){
        diaryRepository.writeDiary(header, diaryId, body)
    }
}