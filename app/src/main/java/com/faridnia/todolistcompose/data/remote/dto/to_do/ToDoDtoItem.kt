package com.faridnia.todolistcompose.data.remote.dto.to_do


import com.google.gson.annotations.SerializedName

data class ToDoDtoItem(
    @SerializedName("completed")
    val completed: Boolean? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("userId")
    val userId: Int? = null
)