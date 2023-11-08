package com.faridnia.todolistcompose.presentaion.ui.login

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
