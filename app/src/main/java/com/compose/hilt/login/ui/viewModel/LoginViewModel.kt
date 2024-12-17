package com.compose.hilt.login.ui.viewModel

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.hilt.login.data.handleErrors.Result
import com.compose.hilt.login.data.model.superheroes.SuperHeroesModel
import com.compose.hilt.login.data.network.response.login.Users
import com.compose.hilt.login.domain.LoginUseCase
import com.compose.hilt.login.ui.interfaces.SuperHeroesUiState
import com.compose.hilt.login.ui.interfaces.SuperHeroesUiState.Success
import com.compose.hilt.login.ui.interfaces.SuperHeroesUiState.Error
import com.compose.hilt.login.ui.interfaces.SuperHeroesUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Lógica de Login
 *
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase //inyecta la clase automaticamente
) : ViewModel() {

    //Consume nuestro caso de uso
    val uiState: StateFlow<SuperHeroesUiState> =
        loginUseCase.getSuperHeroes().map(::Success)//items en success
            .catch { Error(it) } //error en error
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                Loading
            )//flow en stateFlow, se bloquea en segundo plano


    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> = _isProgress

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password =
        MutableLiveData<String>() //mutableLiveData se puede modificar desde dentro de la clase
    val pass: LiveData<String> = _password //el liveData no se puede modificar desde fuera

    private val _isEnabledBtn = MutableLiveData<Boolean>()
    val isEnabledBtn: LiveData<Boolean> = _isEnabledBtn

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


    /** Valida que hayan cambiado los parametros de login para refrescar las vistas */
    fun onParamsChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isEnabledBtn.value = validateLoginSetBtn(email, password)
    }

    private fun validateLoginSetBtn(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


    fun login(username: String, password: String) {
        _isSuccess.value = false
        _isError.value = false

        viewModelScope.launch {
            _isProgress.value = true
            val result = loginUseCase.login(username, password)
            //Handler(Looper.getMainLooper()).postDelayed({
            if (result != null) {
                _isProgress.value = false
                val userList = (result as Result.Success).data.usersResponse.users
                if (userList != null && userList.isNotEmpty()) {

                    if (validateCredentials(userList, username, password)) {
                        _isSuccess.value = true
                        insertSuperHero()
                    } else {
                        _isError.value = true
                    }
                } else {
                    _isError.value = true
                }
            } else {
                //Manejo de errores
                _isProgress.value = false
                _isError.value = true
            }
            // }, 500)
        }
    }

    private fun insertSuperHero() {
        CoroutineScope(Dispatchers.IO).launch {
            setSuperHeroes().forEach {
                loginUseCase.insertSuperHeroes(it)
            }
        }
    }

    private fun setSuperHeroes(): List<SuperHeroesModel> {
        return listOf(
            /*
            SuperHeroesModel(
                id = 0,
                name = "Dev",
                realName = "Juan Carlos",
                power = "Programar",
                affiliation = "X-Men",
                origin = "Monte",
                archEnemy = "Carbohidratos",
                baseOperations = "Ceves House"
            ),
            SuperHeroesModel(
                id = 0,
                name = "Chucky",
                realName = "Samantha",
                power = "Volverse loca",
                affiliation = "X-Men",
                origin = "Luna",
                archEnemy = "Los chismes",
                baseOperations = "Ceves House"
            ),
            SuperHeroesModel(
                id = 0,
                name = "RanaMan",
                realName = "Pepe",
                power = "Lengua de hule",
                affiliation = "X-Men",
                origin = "Pantano",
                archEnemy = "Cocodlile",
                baseOperations = "???"
            )*/
            SuperHeroesModel(
                id = 0,
                name = "RH recruiter",
                realName = "Samantha Soto",
                power = "Psychology",
                affiliation = "JC",
                origin = "CDMX",
                archEnemy = "Carbohidratos",
                baseOperations = "Ceves House"
            )
        )
    }

    private fun validateCredentials(
        userList: List<Users>,
        username: String,
        password: String
    ): Boolean {
        for (i in userList) {
            if (i.user == username) {
                if (i.password == password) {
                    return true
                }
            }
        }
        return false
    }
}