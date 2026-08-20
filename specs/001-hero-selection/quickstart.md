# Quickstart: Test Hero Selection

## Prerequisites

- Android Studio installed with an Android emulator configured.
- The project opened from the repository root and Gradle sync complete.

## Automated checks

1. In Android Studio, open the terminal.
2. Run `./gradlew testDebugUnitTest` (on Windows: `gradlew.bat testDebugUnitTest`).
3. Expect the catalog tests to pass: 45 unique heroes, all groups present, and case-insensitive trimmed searching.

**Result (2026-08-20)**: Passed in Android Studio with 4 tests, then passed again from the repository root using the documented `gradlew.bat --no-daemon testDebugUnitTest` command after the Gradle wrapper was restored.

## Emulator check

1. Start an Android emulator running Android 8.0 or newer.
2. Select that emulator in Android Studio and click Run.
3. Confirm the app opens to the hero selector.
4. Confirm the four headings are visible while browsing and that all 45 names appear once.
5. Search for `vampire` and confirm Vampire Lord appears.
6. Search for `  MILES  ` and confirm Miles Morales Spider-Man appears.
7. Search for `zzz` and confirm the no-results message appears.
8. Clear the search and confirm grouped browsing returns.

**Result (2026-08-20)**: Passed on the Medium Phone API 37.1 emulator. The player confirmed grouped browsing, `vampire`, whitespace/case-insensitive `MILES`, no-result `zzz`, and clearing search all behaved as expected.

**Startup observation (2026-08-20)**: Passed. The player confirmed the hero selector appeared within five seconds after pressing Run in Android Studio.
