package com.compose.hilt.login.data.database.repository

import com.compose.hilt.login.data.database.dao.SuperHeroesDAO
import com.compose.hilt.login.data.database.entities.SuperHeroesEntity
import com.compose.hilt.login.data.model.superheroes.SuperHeroesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/*
* Se utiliza el dao para hacer las consultas
* Flow siempre esta escuchando es reactivo
*
* **/
@Singleton
class SuperHeroesRepository @Inject constructor(private val superHeroesDAO: SuperHeroesDAO) {

    /*val superHeroesLst: Flow<List<SuperHeroesModel>> = superHeroesDAO.getSuperHeroes().map { items ->
        items.map {
            SuperHeroesModel(
                id = it.id,
                name = it.name,
                realName = it.realName,
                power = it.power,
                affiliation = it.affiliation,
                origin = it.origin,
                archEnemy = it.archEnemy,
                baseOperations = it.baseOperations
            )
        }
    }*/


    fun getSuperHeroes(): Flow<List<SuperHeroesModel>> {
        return superHeroesDAO.getSuperHeroes().map { items ->
            items.map {
                SuperHeroesModel(
                    id = it.id,
                    name = it.name,
                    realName = it.realName,
                    power = it.power,
                    affiliation = it.affiliation,
                    origin = it.origin,
                    archEnemy = it.archEnemy,
                    baseOperations = it.baseOperations
                )
            }
        }
    }

    fun insert(superHeroesModel: SuperHeroesModel) {
        superHeroesDAO.insertSuperHero(
            SuperHeroesEntity(
                id = superHeroesModel.id,
                name = superHeroesModel.name,
                realName = superHeroesModel.realName,
                power = superHeroesModel.power,
                affiliation = superHeroesModel.affiliation,
                origin = superHeroesModel.origin,
                archEnemy = superHeroesModel.archEnemy,
                baseOperations = superHeroesModel.baseOperations
            )
        )
    }
}