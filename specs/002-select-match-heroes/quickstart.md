# Quickstart: Select Match Heroes

## Prerequisites

- Android Studio with an Android 8.0+ emulator or device.
- Run `./gradlew.bat testDebugUnitTest` from the project root.

## Manual validation

1. Launch the app. Confirm the opening heading asks for the player's hero and browse shows the four roster groups.
2. Search `vampire`, select **Vampire Lord**, and confirm the opponent step opens showing **Vampire Lord** as the player's choice.
3. Search `  MILES  `, select **Miles Morales Spider-Man**, and confirm both selected heroes are shown.
4. Restart the flow, choose any hero, open the opponent step, use both the in-flow **Back** action and Android system Back in separate runs, and confirm the first choice remains visible and can be changed.
5. Search `zzz` on either choice step and confirm the no-results message appears with no selectable result.
6. After reopening the app from a previously interrupted opponent or confirmation step, confirm it returns safely to **Choose your hero** instead of showing stale choices or crashing.

## Expected result

The flow lets the player choose exactly the two hero fields and does not expose result, first-player, date, review/save, history, statistics, or accounts.

## Validation result (2026-08-20)

Manual device validation completed by the product owner: the two-step flow works, including browse and search on both steps, selecting both heroes, returning to change the logging-player hero with both the in-flow Back button and Android system Back, and safely reopening after an interrupted opponent or confirmation step.

Automated validation completed with the locally cached Java 25 runtime: `gradlew.bat testDebugUnitTest` passed successfully. The ordinary system Java runtime remains Java 8, so the cached Java 25 runtime must be selected when running Gradle locally.
