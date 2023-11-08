package com.faridnia.todolistcompose.data.remote

import com.faridnia.todolistcompose.data.remote.dto.LoginResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ToDoListApi {

    @GET("/users")
    suspend fun users(
        @Query("username") userName: String
    ): LoginResponseDto

    @GET("/todos")
    suspend fun todos()

}