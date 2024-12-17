package com.compose.hilt.login.data.network.dataSource

import com.compose.hilt.login.data.handleErrors.Result
import com.compose.hilt.login.data.network.clients.LoginClient
import com.compose.hilt.login.data.network.response.login.UsersResponse
import com.compose.hilt.login.utils.Constants
import com.compose.hilt.login.utils.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/**
 * Es el encargado de llamar a todos los end point que tuviera el cliente Login de retrofit
 * LoginServie
 */

//Se utiliza el provider para inyectar dependencias de librerias externas como Retrofit

class LoginDataSource @Inject constructor(
    private val loginClient: LoginClient
) {

    private var serverUrl = Constants.path.plus(Constants.apiPrefix)

    private fun getAdapter(endPoint: EndPoints): String? {
        return when(endPoint) {
            EndPoints.Users -> { Constants.users }
            EndPoints.SuperHeroes -> { Constants.superHeroes }
            EndPoints.Success -> { Constants.success }
            EndPoints.Error -> { Constants.error }
            else -> { "" }
        }
    }

    suspend fun login(username: String, password: String): Result<UsersResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response = loginClient.getUsers()
                Result.Success(response.body()!!)
            }
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}