package com.faridnia.todolistcompose.domain.repository

import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoResponseDto

interface ToDoRepository {

    suspend fun todos(): ToDoResponseDto

}