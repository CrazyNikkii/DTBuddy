# Quickstart: Personal Hero Statistics List Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
.\gradlew.bat assembleDebug --no-daemon
```

Expected: the local hero-record tests pass and the debug Android application compiles.

## Manual Android validation

1. Start with clean app data, open **Profile**, choose **Heroes**, and confirm the empty state says no heroes have been played.
2. Save four matches: Barbarian wins twice and loses once; Moon Elf loses once. Use any opponent heroes.
3. Open **Profile** and choose **Heroes**. Confirm the rows are alphabetical and show Barbarian as 3 games, 2 wins, 1 loss, 67%, and Moon Elf as 1 game, 0 wins, 1 loss, 0%.
4. Confirm no opponent-only hero appears in the list.
5. Use Back and confirm Profile is shown again.
6. Save one won match with a new player hero, reopen **Heroes**, and confirm it is added with 1 game, 1 win, 0 losses, and 100%.
7. Close and reopen the app, return to **Heroes**, and confirm all records remain unchanged.

## Validation record

- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using the available Java 25 runtime. Focused tests cover empty, all-win, all-loss, mixed, alphabetical, and opponent-only-hero calculation cases.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully using the same runtime.
- **2026-08-22 manual Android check**: Passed on the running Android emulator. With clean app data, Profile exposed **Heroes** and the page showed its empty state. The exact validation sequence was then completed: Barbarian won twice and lost once; Moon Elf lost once; then a new player hero, Pyromancer, won once. Heroes refreshed after the Pyromancer match and showed alphabetically ordered rows: Barbarian 3 games, 2 wins, 1 loss, 67%; Moon Elf 1 game, 0 wins, 1 loss, 0%; and Pyromancer 1 game, 1 win, 0 losses, 100%. Back returned to Profile, and the same three rows and values remained after force-stopping and reopening the app.
- **2026-08-22 static scope check**: Passed. `git diff --check` reported no whitespace errors. Source review confirmed the change adds only derived personal hero records, their Profile entry point, their list, and focused tests; it adds no hero details, matchup or turn-order statistics, charts, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, or online/global/community features.
