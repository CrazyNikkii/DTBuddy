# Tasks: Local Match History

**Input**: Design documents from `/specs/006-local-match-history/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/local-match-history-ui.md](contracts/local-match-history-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused local unit tests and one instrumented Room DAO test are included because deterministic ordering and correct production-query behaviour are required.

## Phase 1: Setup

**Purpose**: Confirm the existing Android project setup supports this small read-only feature.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Provide the one ordered read operation required by history.

- [X] T002 Add the defined date-played and save-time ordering to the completed-match query in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchDao.kt`.
- [X] T003 Add a repository history read operation in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.

**Checkpoint**: The data boundary can return every saved match in the required order.

## Phase 3: User Story 1 - Review Saved Matches (Priority: P1)

**Goal**: Let a player open and read their locally saved matches from the post-save confirmation.

**Independent Test**: Save several matches, open history from confirmation, and confirm the required information and ordering.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository ordering coverage, including an equal-time ID tie, in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add a real Room DAO ordering test in `app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt`.
- [X] T006 [P] [US1] Add ViewModel history loading coverage in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T007 [US1] Add read-only history state and loading coordination to `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T008 [US1] Add the View match history confirmation action and history destination with empty and populated states in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: History is accessible after a save and presents saved local records without adding any excluded feature.

## Phase 4: Validation

**Purpose**: Verify the approved scope and record outcomes.

- [X] T009 Run `.\gradlew.bat testDebugUnitTest` and record the result in `specs/006-local-match-history/quickstart.md`.
- [X] T010 Compile the instrumented Room DAO test with `.\gradlew.bat assembleDebugAndroidTest` and record the result in `specs/006-local-match-history/quickstart.md`.
- [X] T011 Run `connectedDebugAndroidTest` and record the result in `specs/006-local-match-history/quickstart.md`.
- [X] T012 Run the complete three-match manual Android scenarios in `specs/006-local-match-history/quickstart.md`.
- [X] T013 Review `specs/006-local-match-history/spec.md` against the completed app and confirm that details, editing, deletion, notes, favourites, profiles, accounts, statistics, linking, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002 and T003.
- T002 and T003 precede T004 through T008.
- T004 through T006 precede their related implementation tasks.
- T007 precedes T008.
- T009 through T012 follow T008.

## Parallel Opportunities

- T004 and T005 can run in parallel after the ordered repository read exists.

## Implementation Strategy

1. Reuse the existing completed-match data with an ordered repository read.
2. Add focused tests for ordering and player-perspective results.
3. Add only the confirmation action and read-only history screen.
4. Verify automated and manual scenarios, then confirm excluded scope remains absent.
