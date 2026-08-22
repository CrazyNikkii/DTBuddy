# Implementation Plan: Private Match Notes

**Branch**: `codex/private-match-notes` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

## Summary

Add an optional device-local note to each completed match. Preserve existing Room records with an explicit database migration, extend the established match draft and edit state, and show notes in the existing review, edit-overview, and history screens.

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Language/Version**: Kotlin with Jetpack Compose; Android API 26+.

**Primary Dependencies**: Existing AndroidX Compose, Navigation Compose, Room, and Kotlin coroutines only.

**Storage**: Existing private on-device Room/SQLite database; migrate completed matches from version 1 to version 2.

**Testing**: Existing local repository and ViewModel unit tests, Room instrumentation test compilation, debug build, and manual Android validation.

**Target Platform**: Android 8.0 (API 26) and later.

**Project Type**: Android mobile app.

**Performance Goals**: Notes remain responsive during ordinary local match logging and history use.

**Constraints**: Offline only; notes are private and never logged or sent over a network; no new product dependency or architecture layer; preserve existing local matches during migration.

**Scale/Scope**: One optional plain-text field of at most 500 characters per local completed 1v1 match; existing review, edit, and history screens only.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

Pass before and after design. The feature is one approved Milestone 1 local/offline increment, follows the existing Compose/ViewModel/repository/Room structure, and introduces no account, backend, network, analytics, or future-milestone capability. It requires a narrowly scoped Room migration to prevent loss of existing solo-test data.

## Project Structure

### Documentation (this feature)

```text
specs/015-private-match-notes/
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
│   ├── AppDatabase.kt
│   ├── CompletedMatchDao.kt
│   ├── CompletedMatchEntity.kt
│   └── LocalMatchRepository.kt
└── heroes/
    ├── HeroSelectionScreen.kt
    ├── MatchHeroSelectionState.kt
    └── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/
├── data/LocalMatchRepositoryTest.kt
└── heroes/MatchHeroSelectionViewModelTest.kt

app/src/androidTest/java/com/dtbuddy/app/data/
└── CompletedMatchDaoTest.kt
```

**Structure Decision**: Extend the established single Android app. Room remains behind the existing repository; the existing ViewModel state coordinates Compose-only UI actions.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

No constitution violations require justification.
