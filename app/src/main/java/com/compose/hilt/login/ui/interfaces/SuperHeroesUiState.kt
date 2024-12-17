package com.compose.hilt.login.ui.interfaces

import com.compose.hilt.login.data.model.superheroes.SuperHeroesModel

sealed interface SuperHeroesUiState {

    data object Loading: SuperHeroesUiState
    data class Error(val throwable: Throwable): SuperHeroesUiState
    data class Success(val superHeroesLst: List<SuperHeroesModel>): SuperHeroesUiState

}