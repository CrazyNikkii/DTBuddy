package com.dtbuddy.app.heroes

enum class HeroGroup(val displayName: String) {
    DICE_THRONE("Dice Throne"),
    MARVEL("Marvel"),
    X_MEN("X-Men"),
    STANDALONE_OR_PROMO("Standalone or promo"),
}

data class Hero(
    val name: String,
    val group: HeroGroup,
)
