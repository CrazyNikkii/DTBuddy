# Tasks: Select Match Date

**Input**: Design documents from `/specs/004-select-match-date/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/match-date-flow.md](contracts/match-date-flow.md), and [quickstart.md](quickstart.md)

**Tests**: Focused flow-state unit tests are included because retaining and resetting the temporary date is required behavior.

## Phase 1: Setup

**Purpose**: Confirm the existing Android project and ignore rules support this small extension.

- [X] T001 Verify the existing Kotlin/Compose project and `.gitignore` in `app/build.gradle.kts` and `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define expected date-state behavior before extending the temporary match-choice state.

- [X] T002 Add state-transition unit tests for date retention and dependent-choice resets in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionStateTest.kt`.
- [X] T003 Add date-played state transitions and dependent-choice resets in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.
- [X] T004 Add ViewModel actions for setting and ensuring the date played in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.

## Phase 3: User Story 1 - Choose Date Played (Priority: P1)

**Goal**: Let a player choose a date played after first-player selection and show it in the non-saving temporary summary.

**Independent Test**: Choose both heroes, winner, first player, and a different date; confirm the summary shows all five choices and Back retains the selected date.

- [X] T005 [US1] Add the date-played destination, Android date selector, safe restoration, and five-choice summary in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T006 [US1] Add clear context, Back, and Continue actions to the date-played screen in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 4: Validation

**Purpose**: Verify the approved behavior and retain only this scope.

- [X] T007 Run `gradlew.bat testDebugUnitTest` and record the result in `specs/004-select-match-date/quickstart.md`.
- [X] T008 Run the manual scenarios in `specs/004-select-match-date/quickstart.md` and record the emulator result in that file.
- [X] T009 Review `specs/004-select-match-date/spec.md` against the completed app and confirm that review/save, persistence, history, statistics, notes, favourites, accounts, and network features were not added.

## Dependencies & Execution Order

- T001 precedes T002 through T004.
- T002 precedes T003 and T004.
- T003 and T004 precede T005 and T006.
- T005 and T006 precede validation.

## Parallel Opportunities

- T003 and T004 touch related state files and remain sequential.
- T002 creates the expected behavior before implementation and is completed before UI work.

## Implementation Strategy

1. Extend the small in-memory state with the date-only choice and reset rules.
2. Add the guided date destination and update the temporary summary.
3. Verify state transitions automatically and the full flow manually.
