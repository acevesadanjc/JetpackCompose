package com.compose.hilt.login.domain

import com.compose.hilt.login.data.database.dao.SuperHeroesDAO
import com.compose.hilt.login.data.database.repository.SuperHeroesRepository
import com.compose.hilt.login.data.handleErrors.Result
import com.compose.hilt.login.data.model.superheroes.SuperHeroesModel
import com.compose.hilt.login.data.network.response.login.UsersResponse
import com.compose.hilt.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Clase Casos de Uso
 * - se llama desde el viewModel ya que el es el encargado de llamar a los casos de uso
 **/
class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val superHeroesRepository: SuperHeroesRepository
) {

    /*
    suspend operator fun invoke(username: String, password: String): Result<UsersResponse>? {
        return loginRepository.login(username = username, password = password)
    }
    */

    suspend fun login(username: String, password: String): Result<UsersResponse>? {
        return loginRepository.login(username = username, password = password)
    }

    fun getSuperHeroes(): Flow<List<SuperHeroesModel>> = superHeroesRepository.getSuperHeroes()

    fun insertSuperHeroes(superHero: SuperHeroesModel) = superHeroesRepository.insert(superHero)


}