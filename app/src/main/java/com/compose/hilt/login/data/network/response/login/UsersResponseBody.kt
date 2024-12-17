package com.compose.hilt.login.data.network.response.login

import com.google.gson.annotations.SerializedName

data class UsersResponseBody(
    @SerializedName("Users") val users: List<Users>,
)
