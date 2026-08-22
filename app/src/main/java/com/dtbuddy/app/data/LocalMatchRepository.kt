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

data class PersonalTurnOrderStats(
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

data class PersonalHeroMatchupStats(
    val opponentHeroName: String,
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

data class PersonalHeroTurnOrderDetail(
    val heroName: String,
    val overall: PersonalHeroStats,
    val playerWentFirst: PersonalTurnOrderStats,
    val opponentWentFirst: PersonalTurnOrderStats,
    val matchups: List<PersonalHeroMatchupStats>,
)

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

    suspend fun delete(matchId: Long): Boolean = completedMatchDao.deleteById(matchId) == 1

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

    suspend fun getPersonalHeroTurnOrderDetail(heroName: String): PersonalHeroTurnOrderDetail {
        val heroMatches = completedMatchDao.getHistory().filter { it.playerHeroName == heroName }
        val overall = heroMatches.toPersonalTurnOrderStats()
        return PersonalHeroTurnOrderDetail(
            heroName = heroName,
            overall = PersonalHeroStats(
                heroName = heroName,
                gamesPlayed = overall.gamesPlayed,
                wins = overall.wins,
                losses = overall.losses,
            ),
            playerWentFirst = heroMatches
                .filter { it.firstPlayer == "Player" }
                .toPersonalTurnOrderStats(),
            opponentWentFirst = heroMatches
                .filter { it.firstPlayer == "Opponent" }
                .toPersonalTurnOrderStats(),
            matchups = heroMatches
                .groupBy { it.opponentHeroName }
                .map { (opponentHeroName, matches) ->
                    val wins = matches.count { it.winner == "Player" }
                    PersonalHeroMatchupStats(
                        opponentHeroName = opponentHeroName,
                        gamesPlayed = matches.size,
                        wins = wins,
                        losses = matches.size - wins,
                    )
                }
                .sortedBy { it.opponentHeroName },
        )
    }
}

private fun List<CompletedMatchEntity>.toPersonalTurnOrderStats(): PersonalTurnOrderStats {
    val wins = count { it.winner == "Player" }
    return PersonalTurnOrderStats(
        gamesPlayed = size,
        wins = wins,
        losses = size - wins,
    )
}
