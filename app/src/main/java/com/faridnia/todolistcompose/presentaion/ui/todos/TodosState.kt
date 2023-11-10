package com.faridnia.todolistcompose.presentaion.ui.todos

import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoDtoItem

data class TodosState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val toDoList: List<ToDoDtoItem> = emptyList(),
    val error: String = ""
)
