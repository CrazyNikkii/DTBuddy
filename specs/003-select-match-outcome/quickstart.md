# Quickstart: Select Match Outcome

## Prerequisites

- Android Studio with an Android 8.0+ emulator or device.
- Run `./gradlew.bat testDebugUnitTest` from the project root using the locally cached Java 25 runtime.

## Manual validation

1. Launch the app, choose any logging-player hero and opponent hero, and confirm the `Who won?` screen opens with both heroes visible.
2. Choose `You won`; confirm the `Who went first?` screen opens and retains the selected winner.
3. Choose `Opponent went first`; confirm the `Match details chosen` summary names both heroes, the logging player as winner, and the opponent as first player.
4. In separate runs, use the in-flow Back action and Android system Back from the first-player and winner steps. Confirm the preceding choice is shown and earlier choices remain visible.
5. Return to the hero picker, select a different hero, and confirm the winner and first-player choices must be made again.
6. Interrupt the app on a later choice step and reopen it. Confirm it returns safely to `Choose your hero` rather than showing incomplete stale choices or crashing.

## Expected result

The flow collects only heroes, winner, and first player in temporary memory. It does not expose date selection, review/save, stored matches, history, statistics, accounts, or online features.

## Validation result (2026-08-21)

Automated validation completed with the locally cached Java 25 runtime: `gradlew.bat testDebugUnitTest` passed successfully.

Manual device or emulator validation completed by the product owner: the outcome and first-player flow works as intended, including Android Back, changing a hero after later choices, and safely reopening an interrupted flow.
