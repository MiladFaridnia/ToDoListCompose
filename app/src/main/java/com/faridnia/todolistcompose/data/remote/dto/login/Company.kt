package com.faridnia.todolistcompose.data.remote.dto.login


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("bs")
    val bs: String? = null,
    @SerializedName("catchPhrase")
    val catchPhrase: String? = null,
    @SerializedName("name")
    val name: String? = null
)