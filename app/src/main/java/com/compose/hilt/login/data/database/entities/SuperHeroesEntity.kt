package com.compose.hilt.login.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_super_heroes")
data class SuperHeroesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "realName") val realName: String,
    @ColumnInfo(name = "power") val power: String,
    @ColumnInfo(name = "affiliation") val affiliation: String,
    @ColumnInfo(name = "origin") val origin: String,
    @ColumnInfo(name = "archEnemy") val archEnemy: String,
    @ColumnInfo(name = "baseOperations") val baseOperations: String,
) : Serializable