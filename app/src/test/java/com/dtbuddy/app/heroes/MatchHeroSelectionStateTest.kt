package com.dtbuddy.app.heroes

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MatchHeroSelectionStateTest {
    @Test
    fun selectingBothHeroesAdvancesToConfirmation() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first { it.name == "Vampire Lord" })
            .selectOpponent(HeroCatalog.all.first { it.name == "Miles Morales Spider-Man" })

        assertEquals("Vampire Lord", state.playerHeroName)
        assertEquals("Miles Morales Spider-Man", state.opponentHeroName)
    }

    @Test
    fun changingThePlayerHeroClearsTheOpponentHero() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectPlayer(HeroCatalog.all[2])

        assertEquals("Pyromancer", state.playerHeroName)
        assertNull(state.opponentHeroName)
    }

    @Test
    fun selectingWinnerAndFirstPlayerRetainsAllFourChoices() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectWinner(MatchParticipant.Player)
            .selectFirstPlayer(MatchParticipant.Opponent)

        assertEquals("Barbarian", state.playerHeroName)
        assertEquals("Moon Elf", state.opponentHeroName)
        assertEquals(MatchParticipant.Player, state.winner)
        assertEquals(MatchParticipant.Opponent, state.firstPlayer)
    }

    @Test
    fun changingWinnerClearsOnlyFirstPlayer() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectWinner(MatchParticipant.Player)
            .selectFirstPlayer(MatchParticipant.Player)
            .selectWinner(MatchParticipant.Opponent)

        assertEquals("Barbarian", state.playerHeroName)
        assertEquals("Moon Elf", state.opponentHeroName)
        assertEquals(MatchParticipant.Opponent, state.winner)
        assertNull(state.firstPlayer)
        assertNull(state.datePlayed)
    }

    @Test
    fun changingHeroClearsOutcomeAndTurnOrder() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectWinner(MatchParticipant.Player)
            .selectFirstPlayer(MatchParticipant.Opponent)
            .selectOpponent(HeroCatalog.all[2])

        assertEquals("Pyromancer", state.opponentHeroName)
        assertNull(state.winner)
        assertNull(state.firstPlayer)
        assertNull(state.datePlayed)
    }

    @Test
    fun selectingDateRetainsAllFiveChoices() {
        val datePlayed = LocalDate.of(2026, 8, 20)
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectWinner(MatchParticipant.Player)
            .selectFirstPlayer(MatchParticipant.Opponent)
            .selectDatePlayed(datePlayed)

        assertEquals("Barbarian", state.playerHeroName)
        assertEquals("Moon Elf", state.opponentHeroName)
        assertEquals(MatchParticipant.Player, state.winner)
        assertEquals(MatchParticipant.Opponent, state.firstPlayer)
        assertEquals(datePlayed, state.datePlayed)
    }

    @Test
    fun changingFirstPlayerClearsDate() {
        val state = MatchHeroSelectionState()
            .selectPlayer(HeroCatalog.all.first())
            .selectOpponent(HeroCatalog.all[1])
            .selectWinner(MatchParticipant.Player)
            .selectFirstPlayer(MatchParticipant.Opponent)
            .selectDatePlayed(LocalDate.of(2026, 8, 20))
            .selectFirstPlayer(MatchParticipant.Player)

        assertEquals(MatchParticipant.Player, state.firstPlayer)
        assertNull(state.datePlayed)
    }
}
