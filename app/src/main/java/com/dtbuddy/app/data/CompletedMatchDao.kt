package com.dtbuddy.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompletedMatchDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(match: CompletedMatchEntity): Long

    @Query("SELECT * FROM completed_matches ORDER BY datePlayed DESC, createdAtMillis DESC, id DESC")
    suspend fun getHistory(): List<CompletedMatchEntity>

    @Query("DELETE FROM completed_matches WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query(
        """
        UPDATE completed_matches
        SET playerHeroName = :playerHeroName,
            opponentHeroName = :opponentHeroName,
            winner = :winner,
            firstPlayer = :firstPlayer,
            datePlayed = :datePlayed,
            note = :note
        WHERE id = :id
        """,
    )
    suspend fun updateById(
        id: Long,
        playerHeroName: String,
        opponentHeroName: String,
        winner: String,
        firstPlayer: String,
        datePlayed: String,
        note: String?,
    ): Int
}
