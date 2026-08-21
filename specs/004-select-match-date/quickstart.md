# Quickstart: Select Match Date

## Prerequisites

- Android Studio with an Android 8.0+ emulator or device.
- Run `./gradlew.bat testDebugUnitTest` from the project root using the locally cached Java 25 runtime.

## Manual validation

1. Launch the app, choose any logging-player hero and opponent hero, then choose the winner and first player.
2. Confirm the `When was the match played?` step opens with today's calendar date visible.
3. Select another date through the device date selector and continue. Confirm `Match details chosen` names both heroes, winner, first player, and the selected date.
4. From the summary, use the in-flow Back action and Android system Back in separate runs. Confirm the date step returns with its selected date still visible.
5. Go back, change first player, and continue. Confirm the date selection is reset to today. In a separate run, change the winner or a hero and confirm the later choices must be selected again.
6. Interrupt the app on the date or summary step and reopen it. Confirm it returns safely to `Choose your hero` rather than showing incomplete stale choices or crashing.

## Expected result

The flow collects heroes, winner, first player, and date played only in temporary memory. It does not expose review/save, stored matches, history, statistics, notes, favourites, accounts, or online features.

## Validation result (2026-08-21)

Automated validation completed with the locally cached Java 25 runtime: `gradlew.bat testDebugUnitTest` passed successfully.

Manual emulator validation completed on the local `Medium_Phone` Android 17 AVD. The date step displayed today (21 August 2026), opened the device date picker, retained a changed date (19 August 2026) in the temporary summary, and retained it through both the in-flow Back action and Android system Back. Changing first player reset the date to today. Force-stopping and reopening the app returned safely to `Choose your hero`.
