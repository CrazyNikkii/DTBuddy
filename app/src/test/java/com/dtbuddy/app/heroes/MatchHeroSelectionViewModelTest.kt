package com.dtbuddy.app.heroes

import com.dtbuddy.app.data.CompletedMatchDao
import com.dtbuddy.app.data.CompletedMatchEntity
import com.dtbuddy.app.data.LocalMatchRepository
import java.time.LocalDate
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MatchHeroSelectionViewModelTest {
    @Test
    fun saveCompleteMatchStoresItAndStartsANewEmptyLog() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao) { 10L })
        viewModel.selectPlayer(HeroCatalog.all.first())
        viewModel.selectOpponent(HeroCatalog.all[1])
        viewModel.selectWinner(MatchParticipant.Player)
        viewModel.selectFirstPlayer(MatchParticipant.Opponent)
        viewModel.selectDatePlayed(LocalDate.of(2026, 8, 21))

        assertTrue(viewModel.saveMatch())
        assertEquals(1, dao.matches.size)

        viewModel.startNewMatch()

        assertEquals(MatchHeroSelectionState(), viewModel.state)
    }

    @Test
    fun incompleteMatchCannotBeSaved() = runBlocking {
        val dao = FakeCompletedMatchDao()
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        assertFalse(viewModel.saveMatch())
        assertTrue(dao.matches.isEmpty())
    }

    @Test
    fun secondSaveIsRejectedWhileTheFirstSaveIsInProgress() = runBlocking {
        val dao = BlockingCompletedMatchDao()
        val viewModel = completeViewModel(dao)
        val firstSave = async(start = CoroutineStart.UNDISPATCHED) { viewModel.saveMatch() }

        dao.insertStarted.await()

        assertTrue(viewModel.state.isSaving)
        assertFalse(viewModel.saveMatch())

        dao.allowInsertToFinish.complete(Unit)
        assertTrue(firstSave.await())
        assertEquals(1, dao.matches.size)
    }

    @Test
    fun loadHistoryMakesSavedMatchesAvailableForTheScreen() = runBlocking {
        val dao = FakeCompletedMatchDao().apply {
            matches += CompletedMatchEntity(
                id = 1,
                playerHeroName = "Barbarian",
                opponentHeroName = "Moon Elf",
                winner = "Player",
                firstPlayer = "Opponent",
                datePlayed = "2026-08-21",
                createdAtMillis = 10L,
            )
        }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.loadHistory()

        assertTrue(viewModel.state.hasLoadedHistory)
        assertEquals("Barbarian", viewModel.state.historyMatches.single().playerHeroName)
        assertEquals("Player", viewModel.state.historyMatches.single().winner)
    }

    @Test
    fun loadPersonalOverallStatsMakesTheDerivedSummaryAvailableForProfile() = runBlocking {
        val dao = FakeCompletedMatchDao().apply {
            matches += completedMatch(id = 1, winner = "Player")
            matches += completedMatch(id = 2, winner = "Opponent")
            matches += completedMatch(id = 3, winner = "Player")
        }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.loadPersonalOverallStats()

        assertEquals(3, viewModel.state.personalOverallStats.gamesPlayed)
        assertEquals(2, viewModel.state.personalOverallStats.wins)
        assertEquals(1, viewModel.state.personalOverallStats.losses)
        assertEquals(67, viewModel.state.personalOverallStats.winRatePercentage)
    }

    @Test
    fun loadPersonalHeroStatsMakesDerivedRecordsAvailableForHeroes() = runBlocking {
        val dao = FakeCompletedMatchDao().apply {
            matches += completedMatch(id = 1, playerHeroName = "Moon Elf", winner = "Player")
            matches += completedMatch(id = 2, playerHeroName = "Barbarian", winner = "Opponent")
            matches += completedMatch(id = 3, playerHeroName = "Moon Elf", winner = "Player")
        }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.loadPersonalHeroStats()

        assertEquals(listOf("Barbarian", "Moon Elf"), viewModel.state.personalHeroStats.map { it.heroName })
        assertEquals(1, viewModel.state.personalHeroStats[0].losses)
        assertEquals(2, viewModel.state.personalHeroStats[1].wins)
        assertEquals(100, viewModel.state.personalHeroStats[1].winRatePercentage)
    }

    @Test
    fun loadPersonalHeroTurnOrderDetailMakesSelectedHeroRecordsAvailableForTheScreen() = runBlocking {
        val dao = FakeCompletedMatchDao().apply {
            matches += completedMatch(id = 1, winner = "Player", firstPlayer = "Player")
            matches += completedMatch(id = 2, winner = "Opponent", firstPlayer = "Opponent")
        }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.loadPersonalHeroTurnOrderDetail("Barbarian")

        val detail = viewModel.state.personalHeroTurnOrderDetail
        assertEquals("Barbarian", detail?.heroName)
        assertEquals(2, detail?.overall?.gamesPlayed)
        assertEquals(1, detail?.playerWentFirst?.wins)
        assertEquals(1, detail?.opponentWentFirst?.losses)
    }

    private fun completeViewModel(dao: CompletedMatchDao): MatchHeroSelectionViewModel =
        MatchHeroSelectionViewModel(LocalMatchRepository(dao) { 10L }).also { viewModel ->
            viewModel.selectPlayer(HeroCatalog.all.first())
            viewModel.selectOpponent(HeroCatalog.all[1])
            viewModel.selectWinner(MatchParticipant.Player)
            viewModel.selectFirstPlayer(MatchParticipant.Opponent)
            viewModel.selectDatePlayed(LocalDate.of(2026, 8, 21))
        }

    private fun completedMatch(
        id: Long,
        winner: String,
        playerHeroName: String = "Barbarian",
        firstPlayer: String = "Player",
    ) = CompletedMatchEntity(
        id = id,
        playerHeroName = playerHeroName,
        opponentHeroName = "Moon Elf",
        winner = winner,
        firstPlayer = firstPlayer,
        datePlayed = "2026-08-21",
        createdAtMillis = id,
    )

    private class FakeCompletedMatchDao : CompletedMatchDao {
        val matches = mutableListOf<CompletedMatchEntity>()

        override suspend fun insert(match: CompletedMatchEntity): Long {
            matches += match
            return matches.size.toLong()
        }

        override suspend fun getHistory(): List<CompletedMatchEntity> = matches.toList()
    }

    private class BlockingCompletedMatchDao : CompletedMatchDao {
        val matches = mutableListOf<CompletedMatchEntity>()
        val insertStarted = CompletableDeferred<Unit>()
        val allowInsertToFinish = CompletableDeferred<Unit>()

        override suspend fun insert(match: CompletedMatchEntity): Long {
            insertStarted.complete(Unit)
            allowInsertToFinish.await()
            matches += match
            return matches.size.toLong()
        }

        override suspend fun getHistory(): List<CompletedMatchEntity> = matches.toList()
    }
}
