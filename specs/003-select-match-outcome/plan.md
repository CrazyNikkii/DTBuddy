# Implementation Plan: Select Match Outcome

**Branch**: `codex/select-match-outcome-turn-order` | **Date**: 2026-08-21 | **Spec**: [spec.md](spec.md)

## Summary

Extend the existing in-memory guided match flow with a winner choice and a first-player choice. Retain the choices in the existing ViewModel, reset dependent choices when an earlier answer changes, and show a temporary four-choice summary without saving any match data.

## Technical Context

**Language/Version**: Kotlin in the existing Android project

**Primary Dependencies**: Existing Jetpack Compose, Material 3, Navigation Compose, and Android ViewModel

**Storage**: None for this slice; choices stay only in temporary ViewModel state

**Testing**: Existing JUnit unit-test setup; extend focused flow-state tests and run the app's unit-test task

**Target Platform**: Android 8.0 and later

**Project Type**: Offline Android mobile app

**Performance Goals**: Each choice advances immediately after one tap

**Constraints**: No Room, backend, account, completed-match data, date, review/save, history, or statistics

**Scale/Scope**: Two additional choices and one temporary summary in the existing guided match-log flow

## Constitution Check

| Rule | Result | Evidence |
|---|---|---|
| Source documents take priority | Pass | The work follows the confirmed guided order and excludes all later fields. |
| One small approved piece | Pass | Only result, turn order, and an unsaved summary are added. |
| Milestone 1 remains local/offline | Pass | No network, account, or persistence behavior is introduced. |
| Simple proportionate design | Pass | The existing ViewModel and Navigation Compose flow are extended without new dependencies or layers. |
| Suitable checks | Pass | State-transition unit tests and manual quickstart checks cover the new flow. |

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/heroes/
├── HeroSelectionScreen.kt
├── MatchHeroSelectionState.kt
└── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/heroes/
└── MatchHeroSelectionStateTest.kt

specs/003-select-match-outcome/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/match-outcome-flow.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Keep the small flow in the existing `heroes` feature. The ViewModel owns temporary choices and Navigation Compose owns the choice sequence and Android Back behavior.

## Complexity Tracking

No constitution violations or added complexity require tracking.
