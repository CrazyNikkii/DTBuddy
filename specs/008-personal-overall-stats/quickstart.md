# Quickstart: Personal Overall Statistics Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest
.\gradlew.bat assembleDebug
```

Expected: the local summary tests pass and the debug Android application compiles.

## Manual Android validation

1. Start with clean app data and open **Profile**. Confirm the summary shows 0 games played, 0 wins, 0 losses, and a 0% win rate.
2. Save three matches: two wins and one loss.
3. Open **Profile**. Confirm it shows 3 games played, 2 wins, 1 loss, and a 67% win rate.
4. Use **Match history**. Confirm its three rows account for the same two wins and one loss.
5. Save one additional lost match, reopen **Profile**, and confirm it changes to 4 games, 2 wins, 2 losses, and 50%.
6. Close and reopen the app, open **Profile**, and confirm the four values remain 4, 2, 2, and 50%.

## Validation record

- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using the available Gradle-managed Java runtime.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully using the same runtime.
- **2026-08-22 static scope check**: Passed. `git diff --check` reported no whitespace errors. Source review confirmed the change adds only local overall totals, their Profile display, and their focused tests; it adds no detailed statistics, charts, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, or online feature.
- **2026-08-22 manual Android check**: Passed on the running Android emulator. With clean app data, Profile showed 0 games, 0 wins, 0 losses, and 0%. After two wins and one loss, it showed 3 games, 2 wins, 1 loss, and 67%; Match history contained two Won rows and one Lost row. After a fourth, lost match, it showed 4 games, 2 wins, 2 losses, and 50%. The same values remained after force-closing and reopening the app.
