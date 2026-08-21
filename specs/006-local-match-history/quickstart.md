# Quickstart: Local Match History Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest
```

Expected: all unit tests pass, including the ordered repository history and ViewModel history-state tests.

To compile the instrumented Room-query test, run:

```powershell
.\gradlew.bat assembleDebugAndroidTest
```

To execute it, run `connectedDebugAndroidTest` with a connected Android device or an Android emulator.

## Manual Android validation

1. Start with clean app data and open match history. Confirm the empty message appears.
2. Save three matches: one on an earlier date, then two on the same later date.
3. On the saved confirmation, choose **View match history**.
4. Confirm all three rows show a played date, your hero, opponent hero, and Won or Lost.
5. Confirm the two later same-date matches appear before the earlier match, and the last-saved same-date match appears first.
6. Close and reopen the app, then repeat steps 3–5 to confirm durable local data remains visible.

## Validation record

- **2026-08-21 unit tests**: Passed. `gradlew.bat testDebugUnitTest` completed successfully with a temporary Java 17 runtime.
- **2026-08-21 instrumented-test compilation**: Passed. `gradlew.bat assembleDebugAndroidTest` compiled the Room DAO ordering test successfully.
- **2026-08-21 instrumented Room test**: Passed. `gradlew.bat connectedDebugAndroidTest` ran one test successfully on the connected `Medium_Phone (AVD) - 17` emulator.
- **2026-08-21 manual Android check**: Passed on an Android emulator. The history showed four saved matches with date, both heroes, and a Won or Lost result. The 21 August matches appeared before the 18 August match, and the latest saved 21 August match appeared first. The user also confirmed that saved matches remained available after relaunching the app and saving another test match to reopen history.
- **Static check**: `git diff --check` completed without whitespace errors, and a source search confirmed all DAO implementations use the renamed `getHistory()` operation.
