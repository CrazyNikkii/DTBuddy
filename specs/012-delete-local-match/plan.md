# Implementation Plan: Delete Local Match

**Branch**: `codex/012-delete-local-match` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/012-delete-local-match/spec.md`

**Note**: This template is filled in by the `$speckit-plan` command; its definition describes the execution workflow.

## Summary

Allow the solo player to permanently delete one selected completed local match after an explicit confirmation. Reuse the existing Room data boundary, ViewModel, and Match history screen. Refresh the visible history after deletion; all existing statistics are derived from the remaining stored matches whenever they are next loaded. No schema change, new dependency, or online capability is required.

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Language/Version**: Kotlin with Java 17 toolchain

**Primary Dependencies**: Jetpack Compose, Navigation Compose, Room

**Storage**: Existing Room/SQLite `completed_matches` table in private Android app storage

**Testing**: Existing JUnit local unit tests; existing Android instrumented Room DAO test is extended to cover deletion.

**Target Platform**: Android 8.0 and later (API 26+)

**Project Type**: Android mobile app

**Performance Goals**: A confirmed deletion and history refresh complete without a perceptible delay in the solo-test match list.

**Constraints**: Fully offline; permanent deletion requires a clear cancellation-capable confirmation; delete exactly one selected record; no schema change; do not implement editing, notes, favourites, accounts, networking, or new dependencies.

**Scale/Scope**: One DAO delete operation, one repository and ViewModel operation, focused unit and DAO tests, and a confirmation-capable extension of the existing Match history UI.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- Source documents were read and control scope: **pass**.
- The work is one small, approved Milestone 1 offline feature: **pass**.
- Existing Kotlin, Compose, Room, repository, and ViewModel structure is reused with no new architecture layer or dependency: **pass**.
- The design deletes only user-selected local test data after confirmation and introduces no logging, accounts, networking, or new data collection: **pass**.
- Automated and manual validation are planned: **pass**.

The post-design check remains **pass**: the design uses a primary-key delete within the existing data boundary and a temporary confirmation state only.

## Project Structure

### Documentation (this feature)

```text
specs/[###-feature]/
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
│   ├── CompletedMatchDao.kt             # delete-by-primary-key query
│   └── LocalMatchRepository.kt          # deletion boundary
└── heroes/
    ├── MatchHeroSelectionViewModel.kt   # deletion and history refresh coordination
    ├── MatchHeroSelectionState.kt       # temporary deletion state
    └── HeroSelectionScreen.kt            # history delete action and confirmation

app/src/test/java/com/dtbuddy/app/
├── data/LocalMatchRepositoryTest.kt
└── heroes/MatchHeroSelectionViewModelTest.kt

app/src/androidTest/java/com/dtbuddy/app/data/
└── CompletedMatchDaoTest.kt

specs/012-delete-local-match/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/local-match-deletion-ui.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Extend the established small Android structure. The repository remains the sole Room boundary; the existing ViewModel owns temporary deletion coordination; Compose renders the confirmation and refreshed list.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| [e.g., 4th project] | [current need] | [why 3 projects insufficient] |
| [e.g., Repository pattern] | [specific problem] | [why direct DB access insufficient] |
