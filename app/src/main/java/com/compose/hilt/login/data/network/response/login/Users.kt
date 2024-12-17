package com.compose.hilt.login.data.network.response.login

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String,
    @SerializedName("id") val id: Int
)
