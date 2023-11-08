package com.faridnia.todolistcompose.domain.use_case

import com.faridnia.todolistcompose.data.remote.dto.login.LoginResponseDto
import com.faridnia.todolistcompose.domain.repository.LoginRepository
import com.faridnia.todolistcompose.util.Resource
import com.faridnia.todolistcompose.util.extractErrorMessageFromResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(userName: String): Flow<Resource<LoginResponseDto>> = flow {

        try {
            emit(Resource.Loading())
            val loginResponseDto = loginRepository.login(userName)
            emit(Resource.Success(loginResponseDto))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.extractErrorMessageFromResponse()
                )
            )
        }
    }
}