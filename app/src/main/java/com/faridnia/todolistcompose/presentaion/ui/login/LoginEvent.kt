package com.faridnia.todolistcompose.presentaion.ui.login

sealed class LoginEvent {
    object OnResetLoginState : LoginEvent()

    data class OnLoginClick(val userName: String) : LoginEvent()

}
