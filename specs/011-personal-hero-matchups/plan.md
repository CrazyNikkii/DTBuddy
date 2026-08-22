# Implementation Plan: Personal Hero Matchup Table

**Branch**: `codex/011-personal-hero-matchups` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/011-personal-hero-matchups/spec.md`

**Note**: This template is filled in by the `$speckit-plan` command; its definition describes the execution workflow.

## Summary

Calculate one selected hero's opponent-specific records directly from saved local matches and show them in that hero's existing detail page. Extend the existing Room repository, ViewModel state, and Compose detail screen without changing database storage.

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

**Performance Goals**: The small local matchup table is available when opened for the solo-test match-history size.

**Constraints**: Fully local and offline; calculate from underlying completed matches; no future-milestone features, new dependency, schema migration, or additional architecture layer.

**Scale/Scope**: One existing Profile destination, selected-hero detail, ViewModel, local match repository, and focused unit-test files.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

**Pre-design: PASS.** The source documents require a small Milestone 1 Android increment using Kotlin, Compose, and Room. The design extends the existing local data boundary and adds no backend, account, online data, dependency, or architectural layer.

**Post-design: PASS.** Each matchup record is derived directly from completed local matches and is only another view of the same records. It respects the approved statistics-calculation integrity rule and excludes all future-milestone features.

## Project Structure

### Documentation (this feature)

```text
specs/011-personal-hero-matchups/
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

**Structure Decision**: Extend the existing feature-oriented Android packages. The repository derives opponent-specific records from saved matches, the ViewModel exposes a selected-hero detail containing them, and the existing hero-detail composable renders the table.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
