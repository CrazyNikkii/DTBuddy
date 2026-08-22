# Quickstart: Personal Hero Turn-Order Detail Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
.\gradlew.bat assembleDebug --no-daemon
```

Expected: local repository and ViewModel tests pass, and the debug Android app compiles.

## Manual Android validation

1. Start with clean app data and save four Barbarian matches: win while you went first, loss while you went first, win while the opponent went first, and loss while the opponent went first.
2. Open **Profile**, then **Heroes**, then **Barbarian**. Confirm Overall is 4 games, 2 wins, 2 losses, 50%; **You went first** is 2 games, 1 win, 1 loss, 50%; and **Opponent went first** is the same.
3. Save one Moon Elf win while you went first. Open Moon Elf and confirm Overall and **You went first** are 1 game, 1 win, 0 losses, 100%, while **Opponent went first** is 0 games, 0 wins, 0 losses, 0%.
4. Use the page Back action, then Android system Back from another hero detail, and confirm both return to Heroes.
5. Close and reopen the app, revisit Barbarian, and confirm all values remain unchanged.

## Validation record

- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using the installed Java 25 runtime. Focused tests cover mixed first-player and second-player records, an explicitly all-loss turn-order record, a zero-game turn-order section, 33% rounding for one win in three games, and the rule that every selected-hero match appears in exactly one turn-order section.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully using the same runtime.
- **2026-08-22 static scope check**: Passed. `git diff --check` reported no whitespace errors. Source review confirmed the change adds only derived selected-hero overall and turn-order records, a Heroes-to-detail action, the detail screen, and focused tests; it adds no matchup statistics, charts, sorting controls, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, global/community statistics, or online features.
- **2026-08-22 manual Android check**: Passed on the running Android emulator. Six saved Barbarian test matches produced Overall 6 games, 3 wins, 3 losses, 50%; **You went first** 4 games, 2 wins, 2 losses, 50%; and **Opponent went first** 2 games, 1 win, 1 loss, 50%. With clean app data, a Moon Elf win while going first showed Overall and **You went first** as 1 game, 1 win, 0 losses, 100%, while **Opponent went first** showed 0 games, 0 wins, 0 losses, 0%. Both the page Back action and Android system Back returned to Heroes. After force-stopping and reopening the app, the same Moon Elf values remained.
