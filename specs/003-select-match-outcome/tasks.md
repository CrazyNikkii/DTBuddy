# Tasks: Select Match Outcome

**Input**: Design documents from `/specs/003-select-match-outcome/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/match-outcome-flow.md](contracts/match-outcome-flow.md), and [quickstart.md](quickstart.md)

**Tests**: Focused flow-state unit tests are included because retaining and resetting temporary choices is required behavior.

## Phase 1: Setup

**Purpose**: Confirm the existing project and ignore rules support this small extension.

- [X] T001 Verify the existing Kotlin/Compose project and `.gitignore` in `app/build.gradle.kts` and `.gitignore`.

## Phase 2: Foundational

**Purpose**: Extend the temporary match-choice state shared by all new steps.

- [X] T002 Add participant-role, winner, and first-player state transitions in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.
- [X] T003 Add ViewModel actions for winner and first-player selection in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.

## Phase 3: User Story 1 - Choose Match Outcome and Turn Order (Priority: P1)

**Goal**: Choose the winner and first player after the two heroes, with safe Back navigation and a non-saving four-choice summary.

**Independent Test**: Choose two heroes, winner, and first player; then go Back to correct a choice and complete the flow again.

- [X] T004 [US1] Add state-transition unit tests in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionStateTest.kt`.
- [X] T005 [US1] Add winner, first-player, safe-restoration, and summary destinations to `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T006 [US1] Add clear context and Back actions to the new guided-choice screens in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 4: Validation

**Purpose**: Verify the approved behavior and retain only this scope.

- [X] T007 Run `gradlew.bat testDebugUnitTest` and record the result in `specs/003-select-match-outcome/quickstart.md`.
- [X] T008 Run the manual scenarios in `specs/003-select-match-outcome/quickstart.md` and record the product-owner result in that file.
- [X] T009 Review `specs/003-select-match-outcome/spec.md` against the completed app and confirm that date, review/save, persistence, history, statistics, accounts, and network features were not added.

## Dependencies & Execution Order

- T001 precedes T002 and T003.
- T002 and T003 precede T004 through T006.
- T004 through T006 precede validation.

## Parallel Opportunities

- T002 and T003 touch related state files and remain sequential.
- T004 can be written before the UI work but validates the completed state behavior.

## Implementation Strategy

1. Extend the small in-memory state with the two approved participant choices.
2. Add the two guided choice destinations and temporary summary.
3. Verify state transitions automatically and the full flow manually.
