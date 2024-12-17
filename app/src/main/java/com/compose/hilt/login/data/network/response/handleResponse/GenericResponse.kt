package com.compose.hilt.login.data.network.response.handleResponse

import com.google.gson.annotations.SerializedName

data class GenericResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int
)