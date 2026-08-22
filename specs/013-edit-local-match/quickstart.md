# Quickstart: Edit Local Match

## Automated checks

Run from the repository root:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
.\gradlew.bat assembleDebug --no-daemon
.\gradlew.bat assembleDebugAndroidTest --no-daemon
```

## Manual Android scenarios

1. Save three local matches with deliberately different heroes, outcomes, first-player choices, and dates.
2. From Profile > Match history, choose **Edit match** on the middle record. Change its outcome and date, review, and choose **Save changes**. Confirm history still has three rows, only the selected row changed, and the date has the expected ordering.
3. Reload Profile, Heroes, a hero detail, and Match history. Confirm overall, hero, matchup, and first-player/second-player records agree with the revised three-match set.
4. Start editing a different record, change one or more choices, and leave without saving. Reopen history and statistics; confirm they remain unchanged.
5. Save two otherwise identical matches, edit one, and confirm the other still has its original values.

## Result record

- **2026-08-22 automated checks**: Passed with the locally installed Java 25 runtime for this command session. `testDebugUnitTest --no-daemon`, `assembleDebug --no-daemon`, `assembleDebugAndroidTest --no-daemon`, and `connectedDebugAndroidTest --no-daemon` all completed successfully after the final review fixes. The instrumentation run completed 3 tests on the connected `Medium_Phone (AVD) - 17` emulator.
- **2026-08-22 manual scenarios**: Not run by this implementation task. The emulator is available, but the five hands-on correction scenarios above still require a visual walkthrough; they remain the release-ready manual check.
- **2026-08-22 static scope review**: Passed. `git diff --check` reported no whitespace errors. Source review confirms the feature adds only update-by-ID, a prefilled local edit draft, edit actions, and revised save wording. It adds no notes, favourites, undo, accounts, linked opponents, requests, global/community statistics, or online features.
- **2026-08-22 independent review**: Passed after two must-fix corrections. An unchanged edit now retains its original played date, and a successful edit now ends edit mode so the next saved match is new rather than an overwrite. Both behaviours have focused regression tests.
