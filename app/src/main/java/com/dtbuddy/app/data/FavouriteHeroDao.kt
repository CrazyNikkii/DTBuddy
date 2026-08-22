package com.dtbuddy.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class FavouriteHeroDao {
    @Query("SELECT * FROM favourite_heroes ORDER BY position ASC")
    abstract suspend fun getAll(): List<FavouriteHeroEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(favourite: FavouriteHeroEntity)

    @Query("DELETE FROM favourite_heroes")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun replaceAll(heroNames: List<String>) {
        deleteAll()
        heroNames.forEachIndexed { position, heroName ->
            insert(FavouriteHeroEntity(heroName = heroName, position = position))
        }
    }
}
