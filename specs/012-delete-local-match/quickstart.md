# Quickstart: Delete Local Match Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
```

Expected: all unit tests pass, including deletion-by-ID, cancellation-state, history-refresh, and derived-statistics checks.

To compile and run the Room DAO test, use a connected Android device or emulator:

```powershell
.\gradlew.bat connectedDebugAndroidTest --no-daemon
```

Expected: the instrumented DAO test confirms deletion removes only the requested stored row.

## Manual Android validation

1. Start with clean app data and save three known matches, including two with the same heroes or played date.
2. Open Profile, then Match history. Choose **Delete match** for one row.
3. Confirm the dialog identifies that row's date, both heroes, and Won/Lost result. Choose **Cancel** and confirm all three rows remain.
4. Start deletion for the same row again and choose **Delete match**.
5. Confirm only that row disappears and the other two remain, including an otherwise identical-looking row.
6. Open Profile, Heroes, and the affected hero detail. Confirm games, wins, losses, rates, matchup rows, and turn-order rows match the two remaining saved matches.
7. Close and reopen the app, then revisit Match history and Profile to confirm the deletion remains in effect.

## Validation record

- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using a temporary Java 17 runtime in the ignored `.local-tools/` folder.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully.
- **2026-08-22 instrumented-test compilation**: Passed. `gradlew.bat assembleDebugAndroidTest --no-daemon` compiled the Room DAO deletion test successfully. No Android device or emulator was available on this machine, so `connectedDebugAndroidTest` was not run.
- **2026-08-22 manual Android check**: Passed. The product owner confirmed that cancellation preserved the selected match, confirmed deletion removed only that match, remaining history was correct, and affected Profile statistics refreshed correctly.
- **2026-08-22 static and scope review**: Passed. `git diff --check` found no whitespace errors. The implementation adds only local delete-by-ID, confirmation, and history refresh behaviour; editing, undo, notes, favourites, accounts, linked opponents, requests, global/community statistics, and online features remain excluded.
