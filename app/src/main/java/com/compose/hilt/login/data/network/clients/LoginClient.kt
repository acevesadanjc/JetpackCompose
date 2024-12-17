package com.compose.hilt.login.data.network.clients

import com.compose.hilt.login.data.network.response.handleResponse.GenericResponse
import com.compose.hilt.login.data.network.response.login.UsersResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginClient {

    @GET("users")
    suspend fun getUsers(): Response<UsersResponse>


    @Headers("Connection: close")
    @POST("{path}")
    suspend fun getUsers2(
        @Path(value = "path", encoded = true) path: String?,
    ): Response<UsersResponse>?

    @GET("success")
    suspend fun getSuccessResponse(): Call<GenericResponse>

   @GET("error")
    suspend fun getErrorResponse(): Call<GenericResponse>

    /*
        @Headers("Content-Type: application/x-www-form-urlencoded")
        @POST("{path}")
        fun customer(
            @Path(value = "path", encoded = true) path: String?,
            @Query(value = "params", encoded = true) request: String?
        ): Call<CustomerServiceResponse>
    */
}