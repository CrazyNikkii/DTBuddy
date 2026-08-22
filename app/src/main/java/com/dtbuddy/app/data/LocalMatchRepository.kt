package com.dtbuddy.app.data

import kotlin.math.roundToInt

data class PersonalOverallStats(
    val gamesPlayed: Int = 0,
    val wins: Int = 0,
    val losses: Int = 0,
) {
    val winRatePercentage: Int = if (gamesPlayed == 0) {
        0
    } else {
        (wins.toDouble() / gamesPlayed * 100).roundToInt()
    }
}

data class PersonalHeroStats(
    val heroName: String,
    val gamesPlayed: Int,
    val wins: Int,
    val losses: Int,
) {
    val winRatePercentage: Int = if (gamesPlayed == 0) {
        0
    } else {
        (wins.toDouble() / gamesPlayed * 100).roundToInt()
    }
}

class LocalMatchRepository(
    private val completedMatchDao: CompletedMatchDao,
    private val currentTimeMillis: () -> Long = System::currentTimeMillis,
) {
    suspend fun save(draft: CompletedMatchDraft): Long = completedMatchDao.insert(
        CompletedMatchEntity(
            playerHeroName = draft.playerHeroName,
            opponentHeroName = draft.opponentHeroName,
            winner = draft.winner.name,
            firstPlayer = draft.firstPlayer.name,
            datePlayed = draft.datePlayed.toString(),
            createdAtMillis = currentTimeMillis(),
        ),
    )

    suspend fun getHistory(): List<CompletedMatchEntity> = completedMatchDao.getHistory()

    suspend fun getPersonalOverallStats(): PersonalOverallStats {
        val matches = completedMatchDao.getHistory()
        val wins = matches.count { it.winner == "Player" }
        return PersonalOverallStats(
            gamesPlayed = matches.size,
            wins = wins,
            losses = matches.size - wins,
        )
    }

    suspend fun getPersonalHeroStats(): List<PersonalHeroStats> = completedMatchDao.getHistory()
        .groupBy { it.playerHeroName }
        .map { (heroName, matches) ->
            val wins = matches.count { it.winner == "Player" }
            PersonalHeroStats(
                heroName = heroName,
                gamesPlayed = matches.size,
                wins = wins,
                losses = matches.size - wins,
            )
        }
        .sortedBy { it.heroName }
}
