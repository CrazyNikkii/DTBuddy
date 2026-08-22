# Quickstart: Edit Match Overview

1. Save a local match with Barbarian, Moon Elf, a player win, player first, and a known non-today date.
2. Choose Edit match. Confirm the overview shows five clickable values.
3. Tap First player, choose Opponent, and confirm the overview returns showing only that changed value.
4. Save changes. Reopen history and confirm heroes, winner, and date are unchanged while first player is updated.
5. Start another edit, make a replacement, leave without saving, and confirm the stored match stays unchanged.

## Results

- **2026-08-22 automated checks**: Passed. `testDebugUnitTest --no-daemon` and `assembleDebug --no-daemon` completed successfully using the configured Java 21 JDK.
- **2026-08-22 manual check**: Passed by the product owner. Editing opened the clickable overview; changing one value returned to the overview while preserving unrelated values; saving updated the selected local match as expected.
- **2026-08-22 scope review**: Passed. The change adds only an edit-overview route and edit-only preservation of unrelated values; it adds no data fields, dependencies, notes, undo, accounts, online behaviour, or future-milestone features.
