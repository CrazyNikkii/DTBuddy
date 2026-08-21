# Implementation Plan: Save Local Match

**Branch**: `codex/save-local-match` | **Date**: 2026-08-21 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/005-save-local-match/spec.md`

## Summary

Add a Save match action to the existing temporary match summary. A small Room database, DAO, repository, and manual application container will store a completed unlinked 1v1 match locally. The save action then shows a concise confirmation and starts a fresh in-memory flow when requested.

## Technical Context

**Language/Version**: Kotlin 2.3.21, Java 17

**Primary Dependencies**: Jetpack Compose, Navigation Compose, AndroidX Room 2.8.4 with Kotlin Symbol Processing

**Storage**: Room database backed by private on-device SQLite

**Testing**: JUnit 4 unit tests; manual Android emulator validation

**Target Platform**: Android 8.0+ (API 26)

**Project Type**: Android mobile application

**Performance Goals**: One local save completes without blocking the Compose UI; no network activity.

**Constraints**: Offline-only; save one completed unlinked 1v1 match; no history, statistics, notes, favourites, accounts, linked opponents, or network access.

**Scale/Scope**: One app module, one Room entity/DAO/repository, one existing guided flow, and one confirmation destination.

## Constitution Check

*GATE: Passed before Phase 0 research and re-checked after Phase 1 design.*

`PROJECT.md` and `TECHNICAL_CONSTRAINTS.md` explicitly approve fully local Milestone 1 match logging and Room/SQLite private storage. This slice is one approved useful increment and adds no out-of-scope feature or service. The small repository and manual container are required by the approved application structure.

## Project Structure

### Documentation (this feature)

```text
specs/005-save-local-match/
├── plan.md
├── research.md
├── data-model.md
├── quickstart.md
├── contracts/save-local-match-flow.md
└── tasks.md
```

### Source Code (repository root)

```text
app/src/main/java/com/dtbuddy/app/
├── DTBuddyApplication.kt
├── MainActivity.kt
├── data/
│   ├── AppDatabase.kt
│   ├── CompletedMatchDao.kt
│   ├── CompletedMatchEntity.kt
│   └── LocalMatchRepository.kt
└── heroes/
    ├── HeroSelectionScreen.kt
    └── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/
└── data/
    └── LocalMatchRepositoryTest.kt
```

**Structure Decision**: Keep the existing single Android module. Put Room-specific persistence behind a small data repository and provide it to the screen ViewModel through the approved manual application container.

## Complexity Tracking

No constitution violations or extra architecture were introduced.
