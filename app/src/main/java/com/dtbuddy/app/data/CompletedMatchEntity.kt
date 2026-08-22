package com.dtbuddy.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dtbuddy.app.heroes.MatchParticipant
import java.time.LocalDate

@Entity(tableName = "completed_matches")
data class CompletedMatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val playerHeroName: String,
    val opponentHeroName: String,
    val winner: String,
    val firstPlayer: String,
    val datePlayed: String,
    val createdAtMillis: Long,
    val note: String? = null,
)

data class CompletedMatchDraft(
    val playerHeroName: String,
    val opponentHeroName: String,
    val winner: MatchParticipant,
    val firstPlayer: MatchParticipant,
    val datePlayed: LocalDate,
    val note: String? = null,
)
