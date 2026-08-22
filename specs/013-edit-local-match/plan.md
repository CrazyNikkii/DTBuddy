# Implementation Plan: Edit Local Match

**Branch**: `codex/013-edit-local-match` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/013-edit-local-match/spec.md`

## Summary

Allow a solo player to correct one saved local match from Match history. Reuse the existing guided match-log flow with the selected choices prefilled, update the selected Room record in place, and refresh the history after a save. Existing statistics continue to be derived from the revised local records.

## Technical Context

**Language/Version**: Kotlin on the existing Android project.

**Primary Dependencies**: Jetpack Compose, Navigation Compose, Android ViewModel, Room.

**Storage**: Existing private device-local Room/SQLite database; no schema change.

**Testing**: Existing Kotlin unit tests, Room Android instrumentation tests compiled as an Android test APK, and manual Android scenarios.

**Target Platform**: Android 8.0 / API 26 and newer.

**Project Type**: Android mobile application.

**Performance Goals**: The small local history refresh should remain immediate during solo-test use.

**Constraints**: Fully offline; no new dependencies; change exactly one existing record; retain original history tie-breaker; no notes, favourites, accounts, network, or future-milestone work.

**Scale/Scope**: One local match-correction flow extending existing data, ViewModel, and Compose screen files.

## Constitution Check

- **Source-of-truth priority**: Pass. The feature implements the confirmed Milestone 1 requirement to edit logged matches and follows the approved local Room architecture.
- **Small approved scope**: Pass. It changes only correction of an existing local match.
- **Privacy and milestones**: Pass. No accounts, network access, hosted service, or new user data are introduced.
- **Verification**: Pass. Focused repository, ViewModel, Room, compilation, and manual checks are planned.

## Project Structure

### Documentation

```text
specs/013-edit-local-match/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/local-match-editing-ui.md
├── quickstart.md
└── tasks.md
```

### Source Code

```text
app/src/main/java/com/dtbuddy/app/
├── data/
│   ├── CompletedMatchDao.kt
│   └── LocalMatchRepository.kt
└── heroes/
    ├── HeroSelectionScreen.kt
    ├── MatchHeroSelectionState.kt
    └── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/
├── data/LocalMatchRepositoryTest.kt
└── heroes/MatchHeroSelectionViewModelTest.kt

app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt
```

**Structure Decision**: Extend the existing Room DAO/repository boundary, match-selection ViewModel state, and one Compose screen. No architecture layer, schema migration, or dependency is needed.

## Post-Design Constitution Check

Pass. The design retains the project’s existing local-only data boundary and guided user experience, with no deviation from the source documents or constitution.
