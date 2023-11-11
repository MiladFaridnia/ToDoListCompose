package com.faridnia.todolistcompose.presentaion.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridnia.todolistcompose.Constants.DEFAULT_ERROR
import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoDtoItem
import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoResponseDto
import com.faridnia.todolistcompose.domain.use_case.ToDoUseCase
import com.faridnia.todolistcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ToDosViewModel @Inject constructor(
    private val useCase: ToDoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TodosState())
    val state = _state.asStateFlow()

    init {
        getTodos()
    }

    fun onEvent(event: ToDosEvent) {
        when (event) {
            ToDosEvent.OnResetToDosState -> {
                resetState()
            }
        }
    }

    private fun listIsNotEmpty(toDoResponseDto: ToDoResponseDto?): Boolean {
        return toDoResponseDto?.isNotEmpty() == true
    }

    fun getTodos() {

        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (listIsNotEmpty(result.data)) {
                        result.data?.let { responseDto ->
                            _state.value =
                                TodosState(
                                    isLoading = false,
                                    isSuccess = true,
                                    toDoList = responseDto.map {
                                        ToDoDtoItem(
                                            completed = it.completed,
                                            id = it.id,
                                            title = it.title,
                                            userId = it.userId
                                        )
                                    }
                                )
                        }
                    } else {
                        _state.value =
                            TodosState(
                                isLoading = false,
                                isSuccess = false,
                                error = DEFAULT_ERROR
                            )
                    }
                }

                is Resource.Error -> {
                    _state.value =
                        TodosState(
                            isLoading = false,
                            isSuccess = false,
                            error = result.message ?: DEFAULT_ERROR
                        )
                }

                is Resource.Loading -> {
                    _state.value =
                        TodosState(
                            isLoading = true,
                            isSuccess = false,
                            error = ""
                        )
                }
            }

        }.launchIn(viewModelScope)
    }


    private fun resetState() {
        _state.update { TodosState() }
    }
}