package com.compose.hilt.login.data.repository

import com.compose.hilt.login.data.handleErrors.Result
import com.compose.hilt.login.data.network.response.login.UsersResponse
import com.compose.hilt.login.data.network.dataSource.LoginDataSource
import javax.inject.Inject

/**
 * Clase que solicita autenticación e información de usuario a retrofit
 * Se encarga de decidir a quien le pide la información
 *  - Tiene el api o servicio LoginService o el dominio
 */
class LoginRepository @Inject constructor(
    private val apiDataSource: LoginDataSource
) {

    suspend fun login(username: String, password: String): Result<UsersResponse>? {
        val result = apiDataSource.login(username, password)
        return if (result is Result.Success) {
            result
        } else {
            null
        }
    }

    fun logout() {
        apiDataSource.logout()
    }

}