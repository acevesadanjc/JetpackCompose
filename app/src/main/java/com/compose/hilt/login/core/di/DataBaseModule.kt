package com.compose.hilt.login.core.di

import android.content.Context
import androidx.room.Room
import com.compose.hilt.login.data.database.RoomDatabase
import com.compose.hilt.login.data.database.dao.SuperHeroesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provee la Base de datos y el DAO
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext applicationContext: Context): RoomDatabase {
        return Room.databaseBuilder(applicationContext, RoomDatabase::class.java, "ComposeHiltDB")
            .build()
    }

    @Singleton
    @Provides
    fun provideSuperHeroesDao(roomDatabase: RoomDatabase): SuperHeroesDAO {
        return roomDatabase.getSuperHeroesDAO()
    }

}