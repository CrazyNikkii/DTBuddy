package com.dtbuddy.app.heroes

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HeroCatalogTest {
    @Test
    fun catalogMatchesTheApprovedFrozenRosterAndGroups() {
        val expectedRoster = linkedMapOf(
            "Barbarian" to HeroGroup.DICE_THRONE, "Moon Elf" to HeroGroup.DICE_THRONE,
            "Pyromancer" to HeroGroup.DICE_THRONE, "Shadow Thief" to HeroGroup.DICE_THRONE,
            "Monk" to HeroGroup.DICE_THRONE, "Paladin" to HeroGroup.DICE_THRONE,
            "Ninja" to HeroGroup.DICE_THRONE, "Treant" to HeroGroup.DICE_THRONE,
            "Gunslinger" to HeroGroup.DICE_THRONE, "Samurai" to HeroGroup.DICE_THRONE,
            "Huntress" to HeroGroup.DICE_THRONE, "Tactician" to HeroGroup.DICE_THRONE,
            "Cursed Pirate" to HeroGroup.DICE_THRONE, "Artificer" to HeroGroup.DICE_THRONE,
            "Seraph" to HeroGroup.DICE_THRONE, "Vampire Lord" to HeroGroup.DICE_THRONE,
            "Black Panther" to HeroGroup.MARVEL, "Black Widow" to HeroGroup.MARVEL,
            "Captain Marvel" to HeroGroup.MARVEL, "Doctor Strange" to HeroGroup.MARVEL,
            "Scarlet Witch" to HeroGroup.MARVEL, "Loki" to HeroGroup.MARVEL,
            "Miles Morales Spider-Man" to HeroGroup.MARVEL, "Thor" to HeroGroup.MARVEL,
            "Cyclops" to HeroGroup.X_MEN, "Gambit" to HeroGroup.X_MEN,
            "Iceman" to HeroGroup.X_MEN, "Jean Grey" to HeroGroup.X_MEN,
            "Psylocke" to HeroGroup.X_MEN, "Rogue" to HeroGroup.X_MEN,
            "Storm" to HeroGroup.X_MEN, "Wolverine" to HeroGroup.X_MEN,
            "Santa" to HeroGroup.STANDALONE_OR_PROMO, "Krampus" to HeroGroup.STANDALONE_OR_PROMO,
            "Deadpool" to HeroGroup.STANDALONE_OR_PROMO, "Alchemist" to HeroGroup.STANDALONE_OR_PROMO,
            "Mystic Brawler" to HeroGroup.STANDALONE_OR_PROMO,
            "Headless Horseman" to HeroGroup.STANDALONE_OR_PROMO,
            "Necromancer" to HeroGroup.STANDALONE_OR_PROMO, "Pale Lady" to HeroGroup.STANDALONE_OR_PROMO,
            "Raveness" to HeroGroup.STANDALONE_OR_PROMO, "Forgemaster" to HeroGroup.STANDALONE_OR_PROMO,
            "Druid" to HeroGroup.STANDALONE_OR_PROMO, "Duelist" to HeroGroup.STANDALONE_OR_PROMO,
            "Sun Elf" to HeroGroup.STANDALONE_OR_PROMO,
        )

        assertEquals(expectedRoster, HeroCatalog.all.associate { it.name to it.group })
    }

    @Test
    fun searchTrimsWhitespaceAndIgnoresCapitalization() {
        assertEquals(
            listOf("Miles Morales Spider-Man"),
            HeroCatalog.search("  MILES  ").map { it.name },
        )
    }

    @Test
    fun searchFindsPartialHeroNamesAndCanReturnNoResults() {
        assertEquals(listOf("Vampire Lord"), HeroCatalog.search("vampire").map { it.name })
        assertTrue(HeroCatalog.search("zzz").isEmpty())
    }
}
