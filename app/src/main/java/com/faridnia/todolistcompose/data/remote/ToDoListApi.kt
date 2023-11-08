package com.faridnia.todolistcompose.data.remote

import retrofit2.http.GET

interface ToDoListApi {

    @GET("/users")
    suspend fun users()

    @GET("/todos")
    suspend fun todos()

}