package com.faridnia.todolistcompose.presentaion.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridnia.todolistcompose.Constants.DEFAULT_ERROR
import com.faridnia.todolistcompose.data.remote.dto.login.LoginResponseDto
import com.faridnia.todolistcompose.domain.use_case.LoginUseCase
import com.faridnia.todolistcompose.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val useCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLoginClick -> {
                login(event.userName)
            }

            LoginEvent.OnResetLoginState -> {
                resetState()
            }
        }
    }

    fun login(userName: String) {

        useCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (isLoginSuccessful(result.data)) {
                        _state.value =
                            LoginState(
                                isLoading = false,
                                isSuccess = true,
                            )
                    } else {
                        _state.value =
                            LoginState(
                                isLoading = false,
                                isSuccess = false,
                                error = DEFAULT_ERROR
                            )
                    }
                }

                is Resource.Error -> {
                    _state.value =
                        LoginState(
                            isLoading = false,
                            isSuccess = false,
                            error = result.message ?: DEFAULT_ERROR
                        )
                }

                is Resource.Loading -> {
                    _state.value =
                        LoginState(
                            isLoading = true,
                            isSuccess = false,
                            error = ""
                        )
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun isLoginSuccessful(result: LoginResponseDto?): Boolean {
        return true
    }

    private fun resetState() {
        _state.update { LoginState() }
    }
}