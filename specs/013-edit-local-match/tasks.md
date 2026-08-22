# Tasks: Edit Local Match

**Input**: Design documents from `/specs/013-edit-local-match/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/local-match-editing-ui.md](contracts/local-match-editing-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused repository, ViewModel, and Room DAO tests are included because one selected record must be updated without a duplicate record or incorrect statistics.

## Phase 1: Setup

**Purpose**: Confirm existing Android ignore rules remain sufficient for this local-only change.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Add the selected-record update boundary without changing the schema.

- [X] T002 Add an update-by-primary-key DAO operation in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchDao.kt`.
- [X] T003 Add a repository operation that updates only the selected record's editable match values while retaining its identity and original saved time in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.

**Checkpoint**: The local data boundary can replace exactly one completed record without creating another one.

## Phase 3: User Story 1 - Correct a Saved Local Match (Priority: P1) 🎯 MVP

**Goal**: Let a solo player revise one saved local match through the existing guided choices and see its corrected history and statistics.

**Independent Test**: Save three known matches, edit one through review/save, reopen history and statistics, and compare every result with the revised records.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for exact selected-record replacement, preserved saved time, no duplicate record, and recomputed statistics in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add Room DAO coverage for updating only the selected stored row in `app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt`.
- [X] T006 [US1] Add ViewModel tests for starting a prefilled edit, saving it, refreshing history, and abandoning it without a write in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T007 [US1] Add selected-edit state and ViewModel actions that prefill, save, and discard one edit draft in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T008 [US1] Add Edit match actions, prefilled guided-flow continuation, Save changes wording, and refreshed history behaviour in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can correct one saved local match without adding any future feature.

## Phase 4: Validation

**Purpose**: Verify exact edit behaviour and bounded scope.

- [X] T009 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and record the result in `specs/013-edit-local-match/quickstart.md`.
- [X] T010 Run `.\gradlew.bat assembleDebug --no-daemon` and `.\gradlew.bat assembleDebugAndroidTest --no-daemon`, then record the results in `specs/013-edit-local-match/quickstart.md`.
- [ ] T011 Run the manual Android scenarios in `specs/013-edit-local-match/quickstart.md` and record the result in `specs/013-edit-local-match/quickstart.md`.
- [X] T012 Review `specs/013-edit-local-match/spec.md` against the completed app and confirm notes, favourites, undo, accounts, linked opponents, requests, global/community statistics, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T008.
- T003 precedes T004 and T007.
- T004 and T005 can proceed in parallel after T003; T006 follows the state design in T007.
- T007 precedes T008.
- T009 through T012 follow T008.

## Parallel Opportunities

- T004 and T005 change different test files and can proceed in parallel once the update boundary exists.

## Implementation Strategy

1. Confirm no project setup change is required.
2. Add exact selected-record replacement to the existing DAO and repository.
3. Test state and data behaviour, then make the existing guided flow start from a selected saved record and save changes in place.
4. Run automated and manual checks, then confirm excluded future work remains absent.
