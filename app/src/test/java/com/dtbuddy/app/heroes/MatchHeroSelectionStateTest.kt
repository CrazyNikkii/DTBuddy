package com.dtbuddy.app.heroes

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
}
