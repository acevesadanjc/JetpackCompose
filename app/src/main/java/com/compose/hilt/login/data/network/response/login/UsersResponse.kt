package com.compose.hilt.login.data.network.response.login

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("UsersResponse") val usersResponse: UsersResponseBody,
)
