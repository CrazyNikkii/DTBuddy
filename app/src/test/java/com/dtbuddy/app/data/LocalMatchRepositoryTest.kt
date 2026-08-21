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

    private fun matchDraft(datePlayed: LocalDate) = CompletedMatchDraft(
        playerHeroName = "Barbarian",
        opponentHeroName = "Moon Elf",
        winner = MatchParticipant.Player,
        firstPlayer = MatchParticipant.Opponent,
        datePlayed = datePlayed,
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
    }
}
