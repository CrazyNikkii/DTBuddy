# Tasks: Delete Local Match

**Input**: Design documents from `/specs/012-delete-local-match/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/local-match-deletion-ui.md](contracts/local-match-deletion-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused unit and Room DAO tests are included because deletion must affect exactly one stored record and existing statistics must use only remaining records.

## Phase 1: Setup

**Purpose**: Confirm the existing Android project needs no new infrastructure for this local-only increment.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Add the single-record delete boundary used by the Match history flow.

- [X] T002 Add a delete-by-primary-key DAO operation in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchDao.kt`.
- [X] T003 Add a repository deletion operation that delegates by selected match ID in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.

**Checkpoint**: The data boundary can remove exactly one completed local match without a schema change.

## Phase 3: User Story 1 - Delete an Incorrect Saved Match (Priority: P1) 🎯 MVP

**Goal**: Let a solo player confirm deletion of one selected saved match from Match history and immediately see the remaining local records.

**Independent Test**: Save three known matches, cancel one deletion, confirm another, and verify that only the confirmed selected row disappears and the derived local statistics match the remaining records.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for deletion of exactly the selected ID and recalculation from remaining matches in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add real Room DAO coverage for deleting exactly one stored row in `app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt`.
- [X] T006 [US1] Add ViewModel tests for confirmation cancellation, confirmed deletion, and refreshed history state in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T007 [US1] Add temporary selected-match deletion state and ViewModel actions that cancel or confirm deletion then reload history in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T008 [US1] Add a per-row Delete match action and confirmation dialog with matched date, heroes, result, cancel, and destructive confirmation behaviour in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can safely remove one local record from Match history without reaching a later feature.

## Phase 4: Validation

**Purpose**: Verify the approved scope and exact deletion outcomes.

- [X] T009 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and record the result in `specs/012-delete-local-match/quickstart.md`.
- [X] T010 Run `.\gradlew.bat connectedDebugAndroidTest --no-daemon` when an Android emulator or device is available; otherwise compile the test with `.\gradlew.bat assembleDebugAndroidTest --no-daemon` and record the result in `specs/012-delete-local-match/quickstart.md`.
- [X] T011 Run the manual Android scenarios in `specs/012-delete-local-match/quickstart.md` and record the result in `specs/012-delete-local-match/quickstart.md`.
- [X] T012 Review `specs/012-delete-local-match/spec.md` against the completed app and confirm excluded editing, undo, notes, favourites, accounts, linked opponents, requests, global/community statistics, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T008.
- T003 precedes T004 and T007.
- T004 and T005 can proceed in parallel after T003; T006 follows the ViewModel design in T007.
- T007 precedes T008.
- T009 through T012 follow T008.

## Parallel Opportunities

- T004 and T005 modify different test files and can proceed in parallel once the repository deletion boundary exists.

## Implementation Strategy

1. Confirm the existing ignore rules remain sufficient.
2. Add deletion by the existing stored primary key through the existing DAO and repository boundary.
3. Test the exact-delete and state behaviours, then add a temporary confirmation and refreshed Match history UI.
4. Run automated and manual checks, then confirm the feature stayed within the approved deletion-only scope.
