# Quickstart: Save Local Match

## Prerequisites

- Android Studio with an Android 8.0+ emulator or device.
- Run `./gradlew.bat testDebugUnitTest` from the project root.

## Automated validation

1. Run `./gradlew.bat testDebugUnitTest`.
2. Confirm repository tests verify a complete five-choice match is stored and can be read back with unchanged values.
3. Confirm the focused flow-state tests still pass.

## Manual validation

1. Launch the app and complete the existing hero, winner, first-player, and date choices.
2. On the summary, select `Save match` once.
3. Confirm a clear saved-locally confirmation appears.
4. Select `Log another match` and confirm `Choose your hero` opens without old choices.
5. Close and reopen the app. Confirm the app returns safely to `Choose your hero`; a history or saved-match browser is intentionally not part of this slice.
6. In a separate run, leave the summary without selecting Save match, close the app, and confirm no partial match was stored when later inspecting the database or adding the approved history slice.

## Expected result

One completed unlinked match is stored only on the device. History, statistics, editing, deletion, notes, favourites, accounts, linked opponents, and online features remain unavailable.

## Validation result (2026-08-21)

Automated validation completed with the locally installed Java 25 runtime: `gradlew.bat testDebugUnitTest --console=plain` passed successfully.

Manual Android validation was completed by the product owner. Saving, restarting, starting a fresh log, leaving without saving, and restoring an incomplete destination all worked as expected.
