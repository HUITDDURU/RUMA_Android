package com.example.huitdduru.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BadRequest
import com.example.domain.base.NotFound
import com.example.domain.entity.auth.LoginRequestEntity
import com.example.domain.usecase.auth.LoginUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                loginUseCase.invoke(
                    LoginRequestEntity(
                        email,
                        password
                    )
                )
            }.onSuccess {
                event(Event.SuccessLogin(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 형식입니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다.")) }
                }
            }
        }
    }

    sealed class Event {
        data class SuccessLogin(var state: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}