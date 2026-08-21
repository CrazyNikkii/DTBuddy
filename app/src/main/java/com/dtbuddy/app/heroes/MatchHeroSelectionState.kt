package com.dtbuddy.app.heroes

enum class MatchParticipant {
    Player,
    Opponent,
}

data class MatchHeroSelectionState(
    val playerHeroName: String? = null,
    val opponentHeroName: String? = null,
    val winner: MatchParticipant? = null,
    val firstPlayer: MatchParticipant? = null,
) {
    fun selectPlayer(hero: Hero): MatchHeroSelectionState = copy(
        playerHeroName = hero.name,
        opponentHeroName = null,
        winner = null,
        firstPlayer = null,
    )

    fun selectOpponent(hero: Hero): MatchHeroSelectionState = copy(
        opponentHeroName = hero.name,
        winner = null,
        firstPlayer = null,
    )

    fun selectWinner(participant: MatchParticipant): MatchHeroSelectionState = copy(
        winner = participant,
        firstPlayer = null,
    )

    fun selectFirstPlayer(participant: MatchParticipant): MatchHeroSelectionState = copy(
        firstPlayer = participant,
    )
}
