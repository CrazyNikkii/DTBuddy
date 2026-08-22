# Quickstart: Main Navigation Validation

## Automated checks

From `E:\DTBuddy`, with Java 17 or later selected, run:

```powershell
.\gradlew.bat testDebugUnitTest
.\gradlew.bat assembleDebug
```

Expected: existing unit tests pass and the debug Android application compiles.

## Manual Android validation

1. Launch the app. Confirm **Log match** is selected and the guided log starts at **Choose your hero**.
2. Select **Requests**. Confirm it explains that linked-match requests are not available yet and displays no badge or count.
3. Select **Global stats**. Confirm it explains that community statistics are not available yet and displays no statistic values.
4. Select **Profile**. Confirm it explains that saved data is local to this device and offers **Match history**.
5. Use **Match history** and then the back action. Confirm the existing history screen opens and back returns to Profile.
6. Save a completed match with the existing log flow, close and reopen the app, then repeat steps 4–5. Confirm the saved match remains visible in history.
7. Begin a new guided match, change to another main destination, return to Log match, and confirm no partial match was saved.

## Validation record

- **2026-08-22 static check**: Passed. `git diff --check` completed without whitespace errors.
- **2026-08-22 unit tests**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` completed successfully using a temporary Java 17 runtime outside the project.
- **2026-08-22 debug build**: Passed. `gradlew.bat assembleDebug --no-daemon` completed successfully using the same temporary Java 17 runtime.
- **2026-08-22 manual Android check**: Passed on the product owner's Android environment. Log match was selected on launch; Requests and Global stats showed their correct solo-test messages without badges or statistics; Profile opened Match history and back returned to Profile; a saved match remained visible after app relaunch. Leaving an unfinished log did not save a match. While the app remained open, returning to Log match showed the previously selected hero as temporary in-memory state; it was not stored as a completed match.
- **2026-08-22 review remediation manual check**: Passed on the product owner's Android environment. System Back from Profile → Match history returned to Profile. After selecting another destination, force-closing from Recents, and reopening the app, Log match was selected.
- **2026-08-22 review-remediation automated checks**: Passed. `gradlew.bat testDebugUnitTest --no-daemon` and `gradlew.bat assembleDebug --no-daemon` completed successfully using the temporary Java 17 runtime outside the project.
