# Tasks: Personal Overall Statistics

**Input**: Design documents from `/specs/008-personal-overall-stats/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/personal-overall-stats-ui.md](contracts/personal-overall-stats-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused local unit tests are included because summary correctness for empty, all-win, all-loss, and mixed records is required.

## Phase 1: Setup

**Purpose**: Confirm the existing project setup supports this local-only increment.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define the derived summary value and calculate it from the underlying completed matches.

- [X] T002 Add the derived personal-overall-summary value type and local repository calculation in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.
- [X] T003 Expose summary loading and summary state from the existing ViewModel in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.

**Checkpoint**: The app has one local, derived source for games played, wins, losses, and win rate.

## Phase 3: User Story 1 - View Personal Overall Statistics (Priority: P1)

**Goal**: Let a solo player see their four-value personal overall summary on Profile.

**Independent Test**: Save a known mix of won and lost matches, open Profile, and compare its values with match history.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for zero, all-win, all-loss, and mixed-record calculations in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel coverage proving a loaded personal summary reaches screen state in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T006 [US1] Load and render the labelled Profile summary while preserving the existing Match history action in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: Profile shows accurate device-local overall statistics without exposing a detailed or online statistics feature.

## Phase 4: Validation

**Purpose**: Verify the approved scope and record outcomes.

- [X] T007 Run `.\gradlew.bat testDebugUnitTest` and record the result in `specs/008-personal-overall-stats/quickstart.md`.
- [X] T008 Run `.\gradlew.bat assembleDebug` and record the result in `specs/008-personal-overall-stats/quickstart.md`.
- [X] T009 Run the manual Android scenarios in `specs/008-personal-overall-stats/quickstart.md` and record the result in `specs/008-personal-overall-stats/quickstart.md`.
- [X] T010 Review `specs/008-personal-overall-stats/spec.md` against the completed app and confirm excluded detailed statistics, charts, favourites, notes, match editing, match deletion, accounts, public profiles, linked opponents, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T006.
- T003 precedes T005 and T006.
- T004 and T005 can proceed in parallel after their relevant code exists; the implementation work is kept sequential because it extends shared files.
- T007 through T010 follow T006.

## Parallel Opportunities

- T004 and T005 modify different test files and can run in parallel after the summary calculation and ViewModel state exist.

## Implementation Strategy

1. Confirm the existing ignore rules remain sufficient.
2. Calculate the four values directly from completed local matches and expose the result through the current ViewModel state.
3. Test the calculation and state loading, then add the Profile display.
4. Run automated and manual checks, and confirm that only the approved overall summary was added.
