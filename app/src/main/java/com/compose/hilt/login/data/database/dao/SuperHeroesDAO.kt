package com.compose.hilt.login.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.compose.hilt.login.data.database.entities.SuperHeroesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperHeroesDAO {

    //Se engancha atraves de flow
    @Query("SELECT *  FROM tbl_super_heroes")
    fun getSuperHeroes() : Flow<List<SuperHeroesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperHero(item: SuperHeroesEntity)

    @Update
    fun updateSuperHero(userEntity: SuperHeroesEntity)

    @Query("DELETE FROM tbl_super_heroes WHERE id = :id ")
    fun deleteUser(id: String)
}