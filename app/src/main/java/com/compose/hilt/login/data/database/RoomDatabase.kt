package com.compose.hilt.login.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.hilt.login.data.database.dao.SuperHeroesDAO
import com.compose.hilt.login.data.database.entities.SuperHeroesEntity

@Database(entities = [SuperHeroesEntity::class], version = 1, exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {

    //DAO interfaz que hace las peticiones o consultas al sql de room
    abstract fun getSuperHeroesDAO(): SuperHeroesDAO


}
