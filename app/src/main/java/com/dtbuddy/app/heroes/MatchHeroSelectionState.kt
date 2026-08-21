package com.dtbuddy.app.heroes

import java.time.LocalDate

enum class MatchParticipant {
    Player,
    Opponent,
}

data class MatchHeroSelectionState(
    val playerHeroName: String? = null,
    val opponentHeroName: String? = null,
    val winner: MatchParticipant? = null,
    val firstPlayer: MatchParticipant? = null,
    val datePlayed: LocalDate? = null,
    val isSaving: Boolean = false,
) {
    fun selectPlayer(hero: Hero): MatchHeroSelectionState = copy(
        playerHeroName = hero.name,
        opponentHeroName = null,
        winner = null,
        firstPlayer = null,
        datePlayed = null,
    )

    fun selectOpponent(hero: Hero): MatchHeroSelectionState = copy(
        opponentHeroName = hero.name,
        winner = null,
        firstPlayer = null,
        datePlayed = null,
    )

    fun selectWinner(participant: MatchParticipant): MatchHeroSelectionState = copy(
        winner = participant,
        firstPlayer = null,
        datePlayed = null,
    )

    fun selectFirstPlayer(participant: MatchParticipant): MatchHeroSelectionState = copy(
        firstPlayer = participant,
        datePlayed = null,
    )

    fun selectDatePlayed(date: LocalDate): MatchHeroSelectionState = copy(datePlayed = date)

    fun completedMatchDraftOrNull(): com.dtbuddy.app.data.CompletedMatchDraft? {
        val playerHero = playerHeroName ?: return null
        val opponentHero = opponentHeroName ?: return null
        val selectedWinner = winner ?: return null
        val selectedFirstPlayer = firstPlayer ?: return null
        val selectedDatePlayed = datePlayed ?: return null
        return com.dtbuddy.app.data.CompletedMatchDraft(
            playerHeroName = playerHero,
            opponentHeroName = opponentHero,
            winner = selectedWinner,
            firstPlayer = selectedFirstPlayer,
            datePlayed = selectedDatePlayed,
        )
    }
}
