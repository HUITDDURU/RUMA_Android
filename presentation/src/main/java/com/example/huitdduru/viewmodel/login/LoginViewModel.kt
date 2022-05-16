package com.example.huitdduru.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    sealed class Event {
        data class SuccessLogin(var state: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}