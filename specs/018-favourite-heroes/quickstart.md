# Quickstart: Favourite Heroes Validation

## Automated checks

1. Run `./gradlew.bat testDebugUnitTest --no-daemon`.
2. Run `./gradlew.bat assembleDebug --no-daemon`.
3. Run `./gradlew.bat assembleDebugAndroidTest --no-daemon`.
4. Expected: all commands complete successfully.

## Manual validation

1. Install the debug app with existing local match and private-note data if available. Confirm history and notes still appear.
2. Open Profile, then Favourite heroes. Choose a hero for each of the first, second, and third slots through browse and search.
3. Replace the second slot. Leave the screen and reopen it. Confirm its replacement and the other slots remain unchanged.
4. Start a new match. Confirm Choose your hero shows the ordered Favourites section, and select one.
5. Confirm Choose opponent hero has no Favourites section.
6. Save or discard the match. Confirm favourites are unchanged, and existing history/statistics still work.

## Validation record — 2026-08-22

- Passed: `testDebugUnitTest`, `assembleDebug`, and `assembleDebugAndroidTest`, using the installed JDK 21 because the default shell Java is version 8.
- Passed: the product owner completed the original Android-device manual scenarios.
- Scope review: passed. The change adds only local favourite selection and ordering; it adds no accounts, owned-hero collection, online behaviour, requests, global statistics, sync, or public profile features.
- Passed: the product owner manually confirmed the revised fixed-slot setup and player-only picker behaviour.
