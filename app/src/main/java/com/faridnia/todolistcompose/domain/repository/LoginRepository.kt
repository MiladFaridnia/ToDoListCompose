package com.faridnia.todolistcompose.domain.repository

import com.faridnia.todolistcompose.data.remote.dto.LoginResponseDto

interface LoginRepository {

    suspend fun login(userName: String): LoginResponseDto

}