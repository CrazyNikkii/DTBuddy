package com.dtbuddy.app.heroes

object HeroCatalog {
    val all: List<Hero> = listOf(
        Hero("Barbarian", HeroGroup.DICE_THRONE),
        Hero("Moon Elf", HeroGroup.DICE_THRONE),
        Hero("Pyromancer", HeroGroup.DICE_THRONE),
        Hero("Shadow Thief", HeroGroup.DICE_THRONE),
        Hero("Monk", HeroGroup.DICE_THRONE),
        Hero("Paladin", HeroGroup.DICE_THRONE),
        Hero("Ninja", HeroGroup.DICE_THRONE),
        Hero("Treant", HeroGroup.DICE_THRONE),
        Hero("Gunslinger", HeroGroup.DICE_THRONE),
        Hero("Samurai", HeroGroup.DICE_THRONE),
        Hero("Huntress", HeroGroup.DICE_THRONE),
        Hero("Tactician", HeroGroup.DICE_THRONE),
        Hero("Cursed Pirate", HeroGroup.DICE_THRONE),
        Hero("Artificer", HeroGroup.DICE_THRONE),
        Hero("Seraph", HeroGroup.DICE_THRONE),
        Hero("Vampire Lord", HeroGroup.DICE_THRONE),
        Hero("Black Panther", HeroGroup.MARVEL),
        Hero("Black Widow", HeroGroup.MARVEL),
        Hero("Captain Marvel", HeroGroup.MARVEL),
        Hero("Doctor Strange", HeroGroup.MARVEL),
        Hero("Scarlet Witch", HeroGroup.MARVEL),
        Hero("Loki", HeroGroup.MARVEL),
        Hero("Miles Morales Spider-Man", HeroGroup.MARVEL),
        Hero("Thor", HeroGroup.MARVEL),
        Hero("Cyclops", HeroGroup.X_MEN),
        Hero("Gambit", HeroGroup.X_MEN),
        Hero("Iceman", HeroGroup.X_MEN),
        Hero("Jean Grey", HeroGroup.X_MEN),
        Hero("Psylocke", HeroGroup.X_MEN),
        Hero("Rogue", HeroGroup.X_MEN),
        Hero("Storm", HeroGroup.X_MEN),
        Hero("Wolverine", HeroGroup.X_MEN),
        Hero("Santa", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Krampus", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Deadpool", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Alchemist", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Mystic Brawler", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Headless Horseman", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Necromancer", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Pale Lady", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Raveness", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Forgemaster", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Druid", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Duelist", HeroGroup.STANDALONE_OR_PROMO),
        Hero("Sun Elf", HeroGroup.STANDALONE_OR_PROMO),
    )

    fun search(query: String): List<Hero> {
        val trimmedQuery = query.trim()
        if (trimmedQuery.isEmpty()) return all

        return all.filter { hero ->
            hero.name.contains(trimmedQuery, ignoreCase = true)
        }
    }
}
