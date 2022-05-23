package com.example.huitdduru.viewmodel.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.storage.LocalDataStorage
import com.example.domain.base.BadRequest
import com.example.domain.base.Forbidden
import com.example.domain.base.NotFound
import com.example.domain.base.UnAuthorized
import com.example.domain.entity.diary.DateDiaryResponseEntity
import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.entity.diary.MonthDiaryResponseEntity
import com.example.domain.entity.diary.WriteDiaryRequestEntity
import com.example.domain.usecase.diary.GetDateDiaryUseCase
import com.example.domain.usecase.diary.GetMonthDiaryUseCase
import com.example.domain.usecase.diary.GetDiaryListUseCase
import com.example.domain.usecase.diary.WriteDiaryUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val writeDiaryUseCase: WriteDiaryUseCase,
    private val getDiaryListUseCase: GetDiaryListUseCase,
    private val diaryTimeLineUseCase: GetMonthDiaryUseCase,
    private val getDateDiaryUseCase: GetDateDiaryUseCase,
    private val localDataStorage: LocalDataStorage
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun writeDiary(
        diaryId: Int,
        title: String,
        feeling: String,
        date: LocalDateTime,
        content: String,
        imageUrl: String?
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                writeDiaryUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    diaryId,
                    WriteDiaryRequestEntity(
                        title,
                        feeling,
                        date,
                        content,
                        imageUrl
                    )
                )
            }.onSuccess {
                event(Event.SuccessWrite(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 형식입니다.")) }
                    is UnAuthorized -> { event(Event.ErrorMessage("만료된 토큰입니다.")) }
                    is Forbidden -> { event(Event.ErrorMessage("요청 권한이 없습니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다."))}
                }
            }
        }
    }

    fun getDiaryList(diaryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getDiaryListUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    diaryId
                )
            }.onSuccess {
                Event.SuccessGetList(it)
            }.onFailure {
                when(it) {
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 형식입니다.")) }
                    is UnAuthorized -> { event(Event.ErrorMessage("만료된 토큰입니다.")) }
                    is Forbidden -> { event(Event.ErrorMessage("요청 권한이 없습니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다.")) }
                }
            }
        }
    }

    fun getMonthDiary(year: Int, month: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                diaryTimeLineUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    year,
                    month
                )
            }.onSuccess {
                event(Event.SuccessTimeLine(it))
            }.onFailure {
                when(it) {
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 형식입니다.")) }
                    is UnAuthorized -> { event(Event.ErrorMessage("만료된 토큰입니다.")) }
                    is Forbidden -> { event(Event.ErrorMessage("요청 권한이 없습니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다.")) }
                    }
                }
            }
        }

    fun getDateDiary(date: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getDateDiaryUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    date
                )
            }.onSuccess {
                event(Event.SuccessGetDateDiary(it))
            }.onFailure {
                when(it){
                    is BadRequest -> event(Event.ErrorMessage("잘못된 요청 형식입니다."))
                    is UnAuthorized -> event(Event.ErrorMessage("만료된 토큰입니다."))
                    is Forbidden -> event(Event.ErrorMessage("요청 권한이 없습니다."))
                    is NotFound -> event(Event.ErrorMessage("잘못된 요청입니다."))
                }
            }
        }
    }

    sealed class Event {
        data class SuccessWrite(val status: Boolean = false) : Event()
        data class SuccessGetList(val diaryList : GetDiaryListResponseEntity) : Event()
        data class SuccessTimeLine(val monthDiary : List<MonthDiaryResponseEntity>) : Event()
        data class SuccessGetDateDiary(val dateDiary : List<DateDiaryResponseEntity>) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}


