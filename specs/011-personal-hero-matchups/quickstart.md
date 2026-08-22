# Quickstart: Personal Hero Matchup Table Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
.\gradlew.bat assembleDebug --no-daemon
```

Expected: local repository and ViewModel tests pass, and the debug Android app compiles.

## Manual Android validation

1. Start with clean app data. Save three Barbarian matches against Moon Elf: two wins and one loss. Save two Barbarian matches against Pyromancer: one win and one loss.
2. Open **Profile**, then **Heroes**, then **Barbarian**. Confirm the **Matchups** section has alphabetically ordered Moon Elf and Pyromancer rows.
3. Confirm Moon Elf shows 3 games, 2 wins, 1 loss, 67%, and Pyromancer shows 2 games, 1 win, 1 loss, 50%.
4. Save a later Barbarian loss against Moon Elf, leave and reopen Barbarian, and confirm Moon Elf updates to 4 games, 2 wins, 2 losses, 50%.
5. Close and reopen the app, revisit Barbarian, and confirm the same matchup values remain.

## Validation record

- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using the installed Java 25 runtime. Focused tests cover no matchup rows, grouped mixed results, all-loss results, 67% rounding, alphabetical opponent-hero ordering, exclusion of matches using a different player hero, and the rule that each selected-hero match occurs in exactly one matchup row.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully using the same runtime.
- **2026-08-22 static scope check**: Passed. `git diff --check` reported no whitespace errors. Source review confirmed the change adds only derived local matchup records, their selected-hero-detail display, and focused tests; it adds no global/community statistics, charts, sorting controls, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, or online features.
- **2026-08-22 manual Android check**: Passed. The quickstart scenarios were completed on the emulator: matchup rows were alphabetically ordered, showed the expected mixed records and rounded win rates, refreshed after a later saved match, and remained correct after the app was reopened.
