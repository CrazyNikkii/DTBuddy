# Implementation Plan: Personal Hero Turn-Order Detail

**Branch**: `codex/010-hero-turn-order-stats` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/010-hero-turn-order-stats/spec.md`

**Note**: This template is filled in by the `$speckit-plan` command; its definition describes the execution workflow.

## Summary

Calculate a selected hero's overall, first-player, and second-player records directly from saved local matches. Extend the existing Heroes list so its rows open a focused detail page, using the current Room repository, ViewModel, and Profile navigation.

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Language/Version**: Kotlin targeting Java 17

**Primary Dependencies**: Jetpack Compose, AndroidX lifecycle ViewModel, Room

**Storage**: Existing Room/SQLite `completed_matches` table in private device storage; no schema change

**Testing**: JUnit local unit tests, Android debug compilation, and manual Android validation

**Target Platform**: Android 8.0+ (API 26)

**Project Type**: Android mobile app

**Performance Goals**: The small local detail view is available when opened for the solo-test match-history size.

**Constraints**: Fully local and offline; calculate from underlying completed matches; no future-milestone features, new dependencies, schema migration, or additional architecture layer.

**Scale/Scope**: One existing Profile destination, Heroes list, ViewModel, and local match repository; one selected-hero page with three four-value records.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

**Pre-design: PASS.** The source documents require a small Milestone 1 Android increment using Kotlin, Compose, and Room. The design extends the existing local data boundary and adds no backend, account, online data, dependency, or architectural layer.

**Post-design: PASS.** All records are derived from underlying completed matches. First-player and second-player records partition a selected hero's matches, respecting the approved statistics-calculation integrity rule. Scope excludes matchup and all future-milestone features.

## Project Structure

### Documentation (this feature)

```text
specs/010-hero-turn-order-stats/
├── plan.md              # This file ($speckit-plan command output)
├── research.md          # Phase 0 output ($speckit-plan command)
├── data-model.md        # Phase 1 output ($speckit-plan command)
├── quickstart.md        # Phase 1 output ($speckit-plan command)
├── contracts/           # Phase 1 output ($speckit-plan command)
└── tasks.md             # Phase 2 output ($speckit-tasks command - NOT created by $speckit-plan)
```

### Source Code (repository root)
<!--
  ACTION REQUIRED: Replace the placeholder tree below with the concrete layout
  for this feature. Delete unused options and expand the chosen structure with
  real paths (e.g., apps/admin, packages/something). The delivered plan must
  not include Option labels.
-->

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

**Structure Decision**: Extend the existing feature-oriented Android packages. The repository derives selected-hero records from saved matches, the ViewModel exposes them as screen state, and the existing Profile composable routes the Heroes list to the detail page.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| [e.g., 4th project] | [current need] | [why 3 projects insufficient] |
| [e.g., Repository pattern] | [specific problem] | [why direct DB access insufficient] |
