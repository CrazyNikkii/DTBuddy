package com.dtbuddy.app.heroes

data class MatchHeroSelectionState(
    val playerHeroName: String? = null,
    val opponentHeroName: String? = null,
) {
    fun selectPlayer(hero: Hero): MatchHeroSelectionState = copy(
        playerHeroName = hero.name,
        opponentHeroName = null,
    )

    fun selectOpponent(hero: Hero): MatchHeroSelectionState = copy(
        opponentHeroName = hero.name,
    )
}
