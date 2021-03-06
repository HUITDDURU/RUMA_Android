package com.example.huitdduru.viewmodel.match

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.user.UserInfoResponseEntity
import com.example.domain.usecase.match.*
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    private val cancelUseCase: CancelUseCase,
    private val successUseCase: SuccessUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _sharedFlow = MutableSharedFlow<String>(replay = 0)
    val sharedFlow = _sharedFlow.asSharedFlow()

    private var userInfo : UserInfoResponseEntity? = null

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun receiveMessage(message: String) {
        viewModelScope.launch {
            _sharedFlow.emit(message)
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

    fun matchingAccept(accept: Boolean) {
        viewModelScope.launch {
            matchingAcceptUseCase.invoke(accept)
        }
    }

    fun matchingCancel() {
        viewModelScope.launch {
            matchingCancelUseCase.invoke()
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase.invoke().runCatching {
                collect{
                    event(Event.SuccessFindUser(true))
                    userInfo = it
                }
            }
        }
    }

    fun cancel() {
        viewModelScope.launch {
            kotlin.runCatching {
                cancelUseCase.invoke()
            }.onSuccess { flow ->
                flow.collect { message ->
                    receiveMessage(message)
                    event(Event.SuccessMatch(false))
                    event(Event.SuccessCancel(true))
                }
            }.onFailure {
                event(Event.ErrorMessage("????????? ??????????????????."))
            }
        }
    }

    fun success() {
        viewModelScope.launch {
            kotlin.runCatching {
                successUseCase.invoke()
            }.onSuccess { flow ->
                flow.collect { message ->
                    receiveMessage(message)
                    event(Event.SuccessCancel(false))
                    event(Event.SuccessMatch(true))
                }
            }.onFailure {
                event(Event.ErrorMessage("????????? ??????????????????."))
            }
        }
    }

    fun getUser() = userInfo

    sealed class Event {
        data class SuccessFindUser(var status: Boolean = false) : Event()
        data class SuccessMatch(var status: Boolean = false) : Event()
        data class SuccessCancel(var status: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}