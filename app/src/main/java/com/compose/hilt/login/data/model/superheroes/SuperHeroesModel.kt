package com.compose.hilt.login.data.model.superheroes

data class SuperHeroesModel(
    val id: Int,
    var name: String,
    var realName: String,
    var power: String,
    var affiliation: String,
    var origin: String,
    var archEnemy: String,
    var baseOperations: String
)