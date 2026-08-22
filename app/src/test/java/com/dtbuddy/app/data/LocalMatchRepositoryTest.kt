package com.dtbuddy.app.data

import com.dtbuddy.app.heroes.MatchParticipant
import java.time.LocalDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalMatchRepositoryTest {
    @Test
    fun saveStoresTheFiveSelectedValuesOnce() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao) { 1234L }

        repository.save(
            CompletedMatchDraft(
                playerHeroName = "Barbarian",
                opponentHeroName = "Moon Elf",
                winner = MatchParticipant.Player,
                firstPlayer = MatchParticipant.Opponent,
                datePlayed = LocalDate.of(2026, 8, 21),
            ),
        )

        assertEquals(1, dao.matches.size)
        assertEquals(
            CompletedMatchEntity(
                id = 1,
                playerHeroName = "Barbarian",
                opponentHeroName = "Moon Elf",
                winner = "Player",
                firstPlayer = "Opponent",
                datePlayed = "2026-08-21",
                createdAtMillis = 1234L,
            ),
            dao.matches.single(),
        )
    }

    @Test
    fun historyOrdersByPlayedDateThenMostRecentlySaved() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val timestamps = ArrayDeque(listOf(10L, 20L, 20L))
        val repository = LocalMatchRepository(dao) { timestamps.removeFirst() }

        repository.save(matchDraft(LocalDate.of(2026, 8, 20)))
        repository.save(matchDraft(LocalDate.of(2026, 8, 21)))
        repository.save(matchDraft(LocalDate.of(2026, 8, 21)))

        assertEquals(listOf(3L, 2L, 1L), repository.getHistory().map { it.id })
    }

    @Test
    fun deleteRemovesOnlyTheSelectedMatchAndStatisticsUseRemainingMatches() = runBlocking {
        val dao = FakeCompletedMatchDao().apply {
            matches += savedMatch(id = 1, winner = "Player")
            matches += savedMatch(id = 2, winner = "Opponent")
            matches += savedMatch(id = 3, winner = "Player")
        }
        val repository = LocalMatchRepository(dao)

        assertEquals(true, repository.delete(2))
        assertEquals(listOf(3L, 1L), repository.getHistory().map { it.id })
        assertEquals(PersonalOverallStats(gamesPlayed = 2, wins = 2, losses = 0), repository.getPersonalOverallStats())
        assertEquals(
            PersonalHeroStats("Barbarian", gamesPlayed = 2, wins = 2, losses = 0),
            repository.getPersonalHeroStats().single(),
        )
    }

    @Test
    fun personalOverallStatsHandleEmptyAllWinAllLossAndMixedRecords() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        assertEquals(PersonalOverallStats(), repository.getPersonalOverallStats())

        dao.matches += savedMatch(id = 1, winner = "Player")
        dao.matches += savedMatch(id = 2, winner = "Player")
        assertEquals(
            PersonalOverallStats(gamesPlayed = 2, wins = 2, losses = 0),
            repository.getPersonalOverallStats(),
        )
        assertEquals(100, repository.getPersonalOverallStats().winRatePercentage)

        dao.matches.clear()
        dao.matches += savedMatch(id = 1, winner = "Opponent")
        assertEquals(
            PersonalOverallStats(gamesPlayed = 1, wins = 0, losses = 1),
            repository.getPersonalOverallStats(),
        )
        assertEquals(0, repository.getPersonalOverallStats().winRatePercentage)

        dao.matches += savedMatch(id = 2, winner = "Player")
        dao.matches += savedMatch(id = 3, winner = "Player")
        assertEquals(
            PersonalOverallStats(gamesPlayed = 3, wins = 2, losses = 1),
            repository.getPersonalOverallStats(),
        )
        assertEquals(67, repository.getPersonalOverallStats().winRatePercentage)
    }

    @Test
    fun personalHeroStatsHandleEmptyResultsAndOnlyIncludePlayerHeroes() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        assertEquals(emptyList<PersonalHeroStats>(), repository.getPersonalHeroStats())

        dao.matches += savedMatch(id = 1, playerHeroName = "Barbarian", opponentHeroName = "Moon Elf", winner = "Player")
        dao.matches += savedMatch(id = 2, playerHeroName = "Barbarian", opponentHeroName = "Loki", winner = "Opponent")
        dao.matches += savedMatch(id = 3, playerHeroName = "Moon Elf", opponentHeroName = "Barbarian", winner = "Opponent")
        dao.matches += savedMatch(id = 4, playerHeroName = "Alchemist", opponentHeroName = "Barbarian", winner = "Player")

        assertEquals(
            listOf(
                PersonalHeroStats(heroName = "Alchemist", gamesPlayed = 1, wins = 1, losses = 0),
                PersonalHeroStats(heroName = "Barbarian", gamesPlayed = 2, wins = 1, losses = 1),
                PersonalHeroStats(heroName = "Moon Elf", gamesPlayed = 1, wins = 0, losses = 1),
            ),
            repository.getPersonalHeroStats(),
        )
        assertEquals(100, repository.getPersonalHeroStats()[0].winRatePercentage)
        assertEquals(50, repository.getPersonalHeroStats()[1].winRatePercentage)
        assertEquals(0, repository.getPersonalHeroStats()[2].winRatePercentage)
    }

    @Test
    fun personalHeroTurnOrderDetailSeparatesEverySelectedHeroMatchByTurnOrder() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        dao.matches += savedMatch(id = 1, winner = "Player", firstPlayer = "Player")
        dao.matches += savedMatch(id = 2, winner = "Opponent", firstPlayer = "Player")
        dao.matches += savedMatch(id = 3, winner = "Player", firstPlayer = "Opponent")
        dao.matches += savedMatch(id = 4, winner = "Opponent", firstPlayer = "Opponent")
        dao.matches += savedMatch(id = 5, winner = "Player", playerHeroName = "Moon Elf", firstPlayer = "Player")

        val detail = repository.getPersonalHeroTurnOrderDetail("Barbarian")

        assertEquals(PersonalHeroStats("Barbarian", 4, 2, 2), detail.overall)
        assertEquals(PersonalTurnOrderStats(2, 1, 1), detail.playerWentFirst)
        assertEquals(PersonalTurnOrderStats(2, 1, 1), detail.opponentWentFirst)
        assertEquals(50, detail.overall.winRatePercentage)
        assertEquals(50, detail.playerWentFirst.winRatePercentage)
        assertEquals(50, detail.opponentWentFirst.winRatePercentage)
    }

    @Test
    fun personalHeroTurnOrderDetailShowsEmptyUnusedTurnOrderAndCorrectOneSidedRates() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        dao.matches += savedMatch(id = 1, winner = "Player", firstPlayer = "Player")

        val detail = repository.getPersonalHeroTurnOrderDetail("Barbarian")

        assertEquals(PersonalTurnOrderStats(1, 1, 0), detail.playerWentFirst)
        assertEquals(PersonalTurnOrderStats(), detail.opponentWentFirst)
        assertEquals(100, detail.playerWentFirst.winRatePercentage)
        assertEquals(0, detail.opponentWentFirst.winRatePercentage)
    }

    @Test
    fun personalHeroTurnOrderDetailCoversAllLossesAndRoundsPartialWinRates() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        dao.matches += savedMatch(id = 1, winner = "Opponent", firstPlayer = "Player")
        dao.matches += savedMatch(id = 2, winner = "Opponent", firstPlayer = "Player")
        dao.matches += savedMatch(id = 3, winner = "Player", firstPlayer = "Opponent")
        dao.matches += savedMatch(id = 4, winner = "Opponent", firstPlayer = "Opponent")
        dao.matches += savedMatch(id = 5, winner = "Opponent", firstPlayer = "Opponent")

        val detail = repository.getPersonalHeroTurnOrderDetail("Barbarian")

        assertEquals(PersonalTurnOrderStats(2, 0, 2), detail.playerWentFirst)
        assertEquals(0, detail.playerWentFirst.winRatePercentage)
        assertEquals(PersonalTurnOrderStats(3, 1, 2), detail.opponentWentFirst)
        assertEquals(33, detail.opponentWentFirst.winRatePercentage)
    }

    @Test
    fun personalHeroTurnOrderDetailGroupsEverySelectedHeroMatchIntoAlphabeticalMatchups() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val repository = LocalMatchRepository(dao)

        dao.matches += savedMatch(id = 1, opponentHeroName = "Pyromancer", winner = "Opponent")
        dao.matches += savedMatch(id = 2, opponentHeroName = "Moon Elf", winner = "Player")
        dao.matches += savedMatch(id = 3, opponentHeroName = "Moon Elf", winner = "Opponent")
        dao.matches += savedMatch(id = 4, opponentHeroName = "Moon Elf", winner = "Player")
        dao.matches += savedMatch(id = 5, opponentHeroName = "Loki", winner = "Player")
        dao.matches += savedMatch(id = 6, opponentHeroName = "Loki", winner = "Player")
        dao.matches += savedMatch(id = 7, playerHeroName = "Loki", opponentHeroName = "Moon Elf", winner = "Player")

        val detail = repository.getPersonalHeroTurnOrderDetail("Barbarian")

        assertEquals(
            listOf(
                PersonalHeroMatchupStats("Loki", 2, 2, 0),
                PersonalHeroMatchupStats("Moon Elf", 3, 2, 1),
                PersonalHeroMatchupStats("Pyromancer", 1, 0, 1),
            ),
            detail.matchups,
        )
        assertEquals(100, detail.matchups[0].winRatePercentage)
        assertEquals(67, detail.matchups[1].winRatePercentage)
        assertEquals(0, detail.matchups[2].winRatePercentage)
        assertEquals(detail.overall.gamesPlayed, detail.matchups.sumOf { it.gamesPlayed })
    }

    @Test
    fun personalHeroTurnOrderDetailHasNoMatchupsForAnUnplayedHero() = runBlocking {
        val repository = LocalMatchRepository(FakeCompletedMatchDao())

        assertEquals(emptyList<PersonalHeroMatchupStats>(), repository.getPersonalHeroTurnOrderDetail("Barbarian").matchups)
    }

    private fun matchDraft(datePlayed: LocalDate) = CompletedMatchDraft(
        playerHeroName = "Barbarian",
        opponentHeroName = "Moon Elf",
        winner = MatchParticipant.Player,
        firstPlayer = MatchParticipant.Opponent,
        datePlayed = datePlayed,
    )

    private fun savedMatch(
        id: Long,
        winner: String,
        playerHeroName: String = "Barbarian",
        opponentHeroName: String = "Moon Elf",
        firstPlayer: String = "Player",
    ) = CompletedMatchEntity(
        id = id,
        playerHeroName = playerHeroName,
        opponentHeroName = opponentHeroName,
        winner = winner,
        firstPlayer = firstPlayer,
        datePlayed = "2026-08-21",
        createdAtMillis = id,
    )

    private class FakeCompletedMatchDao : CompletedMatchDao {
        val matches = mutableListOf<CompletedMatchEntity>()

        override suspend fun insert(match: CompletedMatchEntity): Long {
            val saved = match.copy(id = (matches.size + 1).toLong())
            matches += saved
            return saved.id
        }

        override suspend fun getHistory(): List<CompletedMatchEntity> = matches.sortedWith(
            compareByDescending<CompletedMatchEntity> { it.datePlayed }
                .thenByDescending { it.createdAtMillis }
                .thenByDescending { it.id },
        )

        override suspend fun deleteById(id: Long): Int {
            val removed = matches.removeAll { it.id == id }
            return if (removed) 1 else 0
        }
    }
}
