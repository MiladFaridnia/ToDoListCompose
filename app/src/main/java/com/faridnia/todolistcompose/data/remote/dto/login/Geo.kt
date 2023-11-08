package com.faridnia.todolistcompose.data.remote.dto.login


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val lng: String? = null
)