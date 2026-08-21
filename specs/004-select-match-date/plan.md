# Implementation Plan: Select Match Date

**Branch**: `codex/select-match-date` | **Date**: 2026-08-21 | **Spec**: [spec.md](spec.md)

## Summary

Extend the existing in-memory guided match flow with a date-played step. Default it to the device's local calendar date, let the player change it through the Android date selector, retain it in the temporary state, and show it in the non-saving summary.

## Technical Context

**Language/Version**: Kotlin in the existing Android project

**Primary Dependencies**: Existing Jetpack Compose, Material 3, Navigation Compose, Android ViewModel, and Android platform date selector

**Storage**: None for this slice; choices stay only in temporary ViewModel state

**Testing**: Existing JUnit unit-test setup; extend focused flow-state tests and run the app's unit-test task

**Target Platform**: Android 8.0 and later

**Project Type**: Offline Android mobile app

**Performance Goals**: The date step opens immediately after first-player selection; keeping today reaches the summary with one confirmation action

**Constraints**: A date is a calendar date with no time-zone conversion; no Room, draft, completed-match data, review/save, history, statistics, accounts, or network

**Scale/Scope**: One date choice, one date-selector interaction, and an updated temporary summary in the existing guided match-log flow

## Constitution Check

| Rule | Result | Evidence |
|---|---|---|
| Source documents take priority | Pass | The work follows the confirmed guided order and treats the value as a calendar date. |
| One small approved piece | Pass | Only date choice and summary display are added. |
| Milestone 1 remains local/offline | Pass | No network, account, persistence, or saved draft behavior is introduced. |
| Simple proportionate design | Pass | The existing ViewModel and Navigation Compose flow are extended without new libraries or layers. |
| Suitable checks | Pass | State-transition unit tests and manual quickstart checks cover the new behavior. |

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/heroes/
├── HeroSelectionScreen.kt
├── MatchHeroSelectionState.kt
└── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/heroes/
└── MatchHeroSelectionStateTest.kt

specs/004-select-match-date/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/match-date-flow.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Keep the small flow in the existing `heroes` feature. The ViewModel owns temporary choices and Navigation Compose owns the choice sequence and Android Back behavior.

## Complexity Tracking

No constitution violations or added complexity require tracking.
