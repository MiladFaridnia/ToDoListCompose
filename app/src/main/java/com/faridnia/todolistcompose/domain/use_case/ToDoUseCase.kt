package com.faridnia.todolistcompose.domain.use_case

import com.faridnia.todolistcompose.data.remote.dto.to_do.ToDoResponseDto
import com.faridnia.todolistcompose.domain.repository.ToDoRepository
import com.faridnia.todolistcompose.util.Resource
import com.faridnia.todolistcompose.util.extractErrorMessageFromResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {

    operator fun invoke(): Flow<Resource<ToDoResponseDto>> = flow {

        try {
            emit(Resource.Loading())
            val response = toDoRepository.todos()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.extractErrorMessageFromResponse()
                )
            )
        }
    }
}