# Quickstart: Private Match Notes

## Automated checks

Run from the repository root:

```powershell
.\gradlew.bat testDebugUnitTest --no-daemon
.\gradlew.bat assembleDebug --no-daemon
.\gradlew.bat assembleDebugAndroidTest --no-daemon
```

## Manual Android validation

1. Save a match with a short private note. Open Match history and confirm its note appears with the correct match.
2. Save another match with a blank note. Confirm it saves and shows no note area in history.
3. Edit the first match's note, save changes, and confirm its heroes, result, first player, date, and statistics remain unchanged.
4. Start another note edit, change or clear the field, leave without saving, and confirm history still shows the original note.
5. Restart the app and confirm both the saved note and the no-note match remain correct.
6. If upgrading an installation with pre-note records is available, open history and confirm older records remain present without notes.

## Results

- **2026-08-22 automated checks**: Passed. `testDebugUnitTest --no-daemon` passed 40 unit tests; `assembleDebug --no-daemon`, `assembleDebugAndroidTest --no-daemon`, and `connectedDebugAndroidTest --no-daemon` completed successfully using the installed Java 21 JDK. The connected Android suite includes a real version-1 database upgrade check that verifies an existing match is preserved with no note.
- **2026-08-22 manual Android check**: Passed by the product owner. Saving, viewing, and editing private notes behaved as expected.
- **2026-08-22 scope review**: Passed. The change adds one local optional match-note field and its migration only; it adds no favourites, accounts, opponent identity, network behaviour, cloud sync, global statistics, or public features.
