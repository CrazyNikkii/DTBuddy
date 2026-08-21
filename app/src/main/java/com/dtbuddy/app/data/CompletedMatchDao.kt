package com.dtbuddy.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompletedMatchDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(match: CompletedMatchEntity): Long

    @Query("SELECT * FROM completed_matches ORDER BY id")
    suspend fun getAll(): List<CompletedMatchEntity>
}
