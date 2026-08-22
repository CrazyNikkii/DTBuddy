# Implementation Plan: Personal Overall Statistics

**Branch**: `codex/008-personal-overall-stats` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/008-personal-overall-stats/spec.md`

## Summary

Calculate a four-value, device-local personal summary from the existing completed-match records and show it on Profile. Reuse the existing Room repository and Profile screen; add no stored summary data, dependencies, or navigation destinations.

## Technical Context

**Language/Version**: Kotlin targeting Java 17

**Primary Dependencies**: Jetpack Compose, AndroidX lifecycle ViewModel, Room

**Storage**: Existing Room/SQLite `completed_matches` table in private device storage; no schema change

**Testing**: JUnit local unit tests; Android debug compilation; manual Android validation

**Target Platform**: Android 8.0+ (API 26)

**Project Type**: Android mobile app

**Performance Goals**: A local summary is available when Profile opens for the solo-test match history size.

**Constraints**: Fully local and offline; calculate from underlying completed matches; no future-milestone features or new libraries.

**Scale/Scope**: One existing Profile screen, one existing ViewModel, and the local match repository; four summary values only.

## Constitution Check

**Pre-design: PASS.** The source documents require a small, approved Milestone 1 Android increment using Kotlin, Compose, and Room. The design uses the existing local data boundary and introduces no backend, account, online data, dependency, or extra architecture layer.

**Post-design: PASS.** The summary is calculated directly from completed-match records, preserving the approved statistics-integrity rule that broader totals are not summed from narrower displays. Scope remains limited to overall local statistics.

## Project Structure

### Documentation (this feature)

```text
specs/008-personal-overall-stats/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── quickstart.md
├── contracts/
│   └── personal-overall-stats-ui.md
├── checklists/
│   └── requirements.md
└── tasks.md
```

### Source Code (repository root)

```text
app/src/main/java/com/dtbuddy/app/
├── data/
│   └── LocalMatchRepository.kt
└── heroes/
    ├── MatchHeroSelectionState.kt
    ├── MatchHeroSelectionViewModel.kt
    └── HeroSelectionScreen.kt

app/src/test/java/com/dtbuddy/app/
├── data/
│   └── LocalMatchRepositoryTest.kt
└── heroes/
    └── MatchHeroSelectionViewModelTest.kt
```

**Structure Decision**: Extend the existing feature-oriented Android packages. The repository calculates the summary from saved matches, the ViewModel exposes it as screen state, and the existing Profile composable renders it.
