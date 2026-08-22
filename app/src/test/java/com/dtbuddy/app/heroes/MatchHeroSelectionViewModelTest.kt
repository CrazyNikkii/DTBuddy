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
    fun cancellingMatchDeletionKeepsTheSelectedMatch() = runBlocking {
        val match = completedMatch(id = 1, winner = "Player")
        val dao = FakeCompletedMatchDao().apply { matches += match }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.requestMatchDeletion(match)
        viewModel.cancelMatchDeletion()

        assertEquals(null, viewModel.state.pendingDeletionMatch)
        assertEquals(listOf(match), dao.matches)
    }

    @Test
    fun confirmedMatchDeletionRemovesOnlyRequestedMatchAndRefreshesHistory() = runBlocking {
        val first = completedMatch(id = 1, winner = "Player")
        val selected = completedMatch(id = 2, winner = "Opponent")
        val sameLookingMatch = completedMatch(id = 3, winner = "Player")
        val dao = FakeCompletedMatchDao().apply { matches += listOf(first, selected, sameLookingMatch) }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.requestMatchDeletion(selected)

        assertTrue(viewModel.confirmMatchDeletion())
        assertEquals(null, viewModel.state.pendingDeletionMatch)
        assertTrue(viewModel.state.hasLoadedHistory)
        assertEquals(listOf(first, sameLookingMatch), viewModel.state.historyMatches)
    }

    @Test
    fun editingPrefillsTheSelectedMatchAndSavesOnlyItsRevisedValues() = runBlocking {
        val selected = completedMatch(id = 1, winner = "Player", firstPlayer = "Opponent")
        val untouched = completedMatch(id = 2, winner = "Opponent", opponentHeroName = "Loki")
        val dao = FakeCompletedMatchDao().apply { matches += listOf(selected, untouched) }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(selected)

        assertTrue(viewModel.state.isEditing)
        assertEquals("Barbarian", viewModel.state.playerHeroName)
        assertEquals("Moon Elf", viewModel.state.opponentHeroName)
        assertEquals(MatchParticipant.Player, viewModel.state.winner)
        assertEquals(MatchParticipant.Opponent, viewModel.state.firstPlayer)
        assertEquals(LocalDate.of(2026, 8, 21), viewModel.state.datePlayed)

        viewModel.selectOpponent(HeroCatalog.all.first { it.name == "Loki" })
        viewModel.selectWinner(MatchParticipant.Opponent)
        viewModel.selectFirstPlayer(MatchParticipant.Player)
        viewModel.selectDatePlayed(LocalDate.of(2026, 8, 22))

        assertTrue(viewModel.saveMatch())
        assertEquals(2, dao.matches.size)
        assertEquals("Loki", dao.matches.single { it.id == 1L }.opponentHeroName)
        assertEquals("Opponent", dao.matches.single { it.id == 1L }.winner)
        assertEquals("Player", dao.matches.single { it.id == 1L }.firstPlayer)
        assertEquals("2026-08-22", dao.matches.single { it.id == 1L }.datePlayed)
        assertEquals(untouched, dao.matches.single { it.id == 2L })
        assertEquals(listOf(1L, 2L), viewModel.state.historyMatches.map { it.id })
    }

    @Test
    fun startingAnEditDoesNotWriteUntilThePlayerSaves() = runBlocking {
        val match = completedMatch(id = 1, winner = "Player")
        val dao = FakeCompletedMatchDao().apply { matches += match }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(match)
        viewModel.selectWinner(MatchParticipant.Opponent)

        assertEquals(listOf(match), dao.matches)
    }

    @Test
    fun discardingAnEditClearsOnlyTheDraftAndDoesNotWriteTheStoredMatch() {
        val match = completedMatch(id = 1, winner = "Player")
        val dao = FakeCompletedMatchDao().apply { matches += match }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(match, returnToProfileHistory = true)
        viewModel.selectWinner(MatchParticipant.Opponent)
        viewModel.discardEditing()

        assertFalse(viewModel.state.isEditing)
        assertFalse(viewModel.state.editReturnToProfileHistory)
        assertEquals(listOf(match), dao.matches)
    }

    @Test
    fun noteDraftIsLimitedPrefilledAndOnlySavedWhenTheEditIsSaved() = runBlocking {
        val match = completedMatch(id = 1, winner = "Player").copy(note = "Original note")
        val dao = FakeCompletedMatchDao().apply { matches += match }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(match)
        viewModel.selectNote("Updated note")
        assertEquals("Updated note", viewModel.state.note)
        assertEquals("Original note", dao.matches.single().note)

        assertTrue(viewModel.saveMatch())
        assertEquals("Updated note", dao.matches.single().note)

        viewModel.startNewMatch()
        viewModel.selectNote("x".repeat(501))
        assertEquals(500, viewModel.state.note.length)
    }

    @Test
    fun changingOneValueWhileEditingKeepsEveryOtherEditValue() {
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(FakeCompletedMatchDao()))
        viewModel.startEditing(
            completedMatch(
                id = 1,
                winner = "Player",
                playerHeroName = "Barbarian",
                opponentHeroName = "Moon Elf",
                firstPlayer = "Player",
            ),
        )

        viewModel.selectFirstPlayer(MatchParticipant.Opponent)

        assertEquals("Barbarian", viewModel.state.playerHeroName)
        assertEquals("Moon Elf", viewModel.state.opponentHeroName)
        assertEquals(MatchParticipant.Player, viewModel.state.winner)
        assertEquals(MatchParticipant.Opponent, viewModel.state.firstPlayer)
        assertEquals(LocalDate.of(2026, 8, 21), viewModel.state.datePlayed)
    }

    @Test
    fun changingAValueInANewMatchStillClearsLaterGuidedChoices() {
        val viewModel = completeViewModel(FakeCompletedMatchDao())

        viewModel.selectWinner(MatchParticipant.Opponent)

        assertEquals(MatchParticipant.Opponent, viewModel.state.winner)
        assertEquals(null, viewModel.state.firstPlayer)
        assertEquals(null, viewModel.state.datePlayed)
    }

    @Test
    fun unchangedEditKeepsItsOriginalDateWhenTheGuidedChoicesAreReselected() = runBlocking {
        val match = completedMatch(id = 1, winner = "Player", firstPlayer = "Opponent")
        val dao = FakeCompletedMatchDao().apply { matches += match }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(match)
        viewModel.selectPlayer(HeroCatalog.all.first { it.name == "Barbarian" })
        viewModel.selectOpponent(HeroCatalog.all.first { it.name == "Moon Elf" })
        viewModel.selectWinner(MatchParticipant.Player)
        viewModel.selectFirstPlayer(MatchParticipant.Opponent)
        viewModel.ensureDatePlayed(LocalDate.of(2026, 8, 22))

        assertEquals(LocalDate.of(2026, 8, 21), viewModel.state.datePlayed)
        assertTrue(viewModel.saveMatch())
        assertEquals("2026-08-21", dao.matches.single().datePlayed)
    }

    @Test
    fun successfulEditEndsEditModeSoTheNextSavedMatchIsNew() = runBlocking {
        val edited = completedMatch(id = 1, winner = "Player")
        val dao = FakeCompletedMatchDao().apply { matches += edited }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.startEditing(edited)
        assertTrue(viewModel.saveMatch())
        assertFalse(viewModel.state.isEditing)
        assertTrue(viewModel.state.lastSaveWasEdit)

        viewModel.selectPlayer(HeroCatalog.all.first { it.name == "Moon Elf" })
        viewModel.selectOpponent(HeroCatalog.all.first { it.name == "Loki" })
        viewModel.selectWinner(MatchParticipant.Opponent)
        viewModel.selectFirstPlayer(MatchParticipant.Player)
        viewModel.ensureDatePlayed(LocalDate.of(2026, 8, 22))

        assertTrue(viewModel.saveMatch())
        assertEquals(2, dao.matches.size)
        assertEquals(edited, dao.matches.single { it.id == 1L })
        assertFalse(viewModel.state.lastSaveWasEdit)
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
            matches += completedMatch(id = 1, winner = "Player", firstPlayer = "Player", opponentHeroName = "Moon Elf")
            matches += completedMatch(id = 2, winner = "Opponent", firstPlayer = "Opponent", opponentHeroName = "Pyromancer")
        }
        val viewModel = MatchHeroSelectionViewModel(LocalMatchRepository(dao))

        viewModel.loadPersonalHeroTurnOrderDetail("Barbarian")

        val detail = viewModel.state.personalHeroTurnOrderDetail
        assertEquals("Barbarian", detail?.heroName)
        assertEquals(2, detail?.overall?.gamesPlayed)
        assertEquals(1, detail?.playerWentFirst?.wins)
        assertEquals(1, detail?.opponentWentFirst?.losses)
        assertEquals(listOf("Moon Elf", "Pyromancer"), detail?.matchups?.map { it.opponentHeroName })
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
        opponentHeroName: String = "Moon Elf",
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
            matches += match
            return matches.size.toLong()
        }

        override suspend fun getHistory(): List<CompletedMatchEntity> = matches.toList()

        override suspend fun deleteById(id: Long): Int {
            val removed = matches.removeAll { it.id == id }
            return if (removed) 1 else 0
        }

        override suspend fun updateById(
            id: Long,
            playerHeroName: String,
            opponentHeroName: String,
            winner: String,
            firstPlayer: String,
            datePlayed: String,
            note: String?,
        ): Int {
            val index = matches.indexOfFirst { it.id == id }
            if (index == -1) return 0
            matches[index] = matches[index].copy(
                playerHeroName = playerHeroName,
                opponentHeroName = opponentHeroName,
                winner = winner,
                firstPlayer = firstPlayer,
                datePlayed = datePlayed,
                note = note,
            )
            return 1
        }
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

        override suspend fun deleteById(id: Long): Int = 0

        override suspend fun updateById(
            id: Long,
            playerHeroName: String,
            opponentHeroName: String,
            winner: String,
            firstPlayer: String,
            datePlayed: String,
            note: String?,
        ): Int = 0
    }
}
