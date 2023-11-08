package com.faridnia.todolistcompose.data.remote.dto.to_do


import com.google.gson.annotations.SerializedName

data class ToDoDtoItem(
    @SerializedName("completed")
    val completed: Boolean? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("userId")
    val userId: Int? = null
)