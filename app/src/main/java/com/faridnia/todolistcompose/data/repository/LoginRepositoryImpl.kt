package com.faridnia.todolistcompose.data.repository

import com.faridnia.todolistcompose.data.remote.ToDoListApi
import com.faridnia.todolistcompose.data.remote.dto.LoginResponseDto
import com.faridnia.todolistcompose.domain.repository.LoginRepository

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: ToDoListApi) : LoginRepository {
    override suspend fun login(userName:String): LoginResponseDto {
        return api.users(userName)
    }
}