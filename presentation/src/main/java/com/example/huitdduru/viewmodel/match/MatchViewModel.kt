package com.example.huitdduru.viewmodel.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.*
import com.example.domain.usecase.match.*
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import com.example.huitdduru.viewmodel.diary.DiaryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val localMatchingUseCase: LocalMatchingUseCase,
    private val matchingAcceptUseCase: MatchingAcceptUseCase,
    private val matchingCancelUseCase: MatchingCancelUseCase,
    private val matchingUseCase: MatchingUseCase,
    private val cancel: CancelUseCase,
    private val success: SuccessUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun connect(){
        viewModelScope.launch {
            connectSocketUseCase.invoke()
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            disconnectSocketUseCase.invoke()
        }
    }

    fun matching() {
        viewModelScope.launch {
            matchingUseCase.invoke()
        }
    }

    fun localMatching(code: String) {
        viewModelScope.launch {
            localMatchingUseCase.invoke(code)
        }
    }



    sealed class Event {
    }
}