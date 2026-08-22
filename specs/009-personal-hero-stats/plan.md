# Implementation Plan: Personal Hero Statistics List

**Branch**: `codex/009-personal-hero-stats` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/009-personal-hero-stats/spec.md`

## Summary

Calculate one derived local record per logging-player hero from the existing completed-match records, then add a Heroes action and list within Profile. Reuse the existing Room repository, ViewModel, and Profile composable; add no stored statistics, schema change, dependencies, or navigation destination.

## Technical Context

**Language/Version**: Kotlin targeting Java 17

**Primary Dependencies**: Jetpack Compose, AndroidX lifecycle ViewModel, Room

**Storage**: Existing Room/SQLite `completed_matches` table in private device storage; no schema change

**Testing**: JUnit local unit tests; Android debug compilation; manual Android validation

**Target Platform**: Android 8.0+ (API 26)

**Project Type**: Android mobile app

**Performance Goals**: The small local hero list is available when Heroes opens for the solo-test match-history size.

**Constraints**: Fully local and offline; calculate from underlying completed matches; no future-milestone features or new libraries.

**Scale/Scope**: One existing Profile screen, one existing ViewModel, and the local match repository; a flat hero list and four values per row only.

## Constitution Check

**Pre-design: PASS.** The source documents require a small Milestone 1 Android increment using Kotlin, Compose, and Room. The design uses the existing local data boundary and introduces no backend, account, online data, dependency, or additional architecture layer.

**Post-design: PASS.** Hero records are calculated directly from completed match records, preserving the approved statistics-integrity rule that broader totals are not summed from narrower displays. Scope remains limited to a simple personal hero list.

## Project Structure

### Documentation (this feature)

```text
specs/009-personal-hero-stats/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── quickstart.md
├── contracts/
│   └── personal-hero-stats-ui.md
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

**Structure Decision**: Extend the existing feature-oriented Android packages. The repository derives per-hero records from saved matches, the ViewModel exposes them as screen state, and the existing Profile composable provides the action and list.
