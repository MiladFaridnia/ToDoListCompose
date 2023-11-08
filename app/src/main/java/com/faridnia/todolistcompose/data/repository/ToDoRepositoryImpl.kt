package com.faridnia.todolistcompose.data.repository

import com.faridnia.todolistcompose.data.remote.ToDoListApi
import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoResponseDto
import com.faridnia.todolistcompose.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val api: ToDoListApi) : ToDoRepository {
    override suspend fun todos(): ToDoResponseDto {
        return api.todos()
    }
}