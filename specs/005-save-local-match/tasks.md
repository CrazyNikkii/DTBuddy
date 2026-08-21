# Tasks: Save Local Match

**Input**: Design documents from `/specs/005-save-local-match/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/save-local-match-flow.md](contracts/save-local-match-flow.md), and [quickstart.md](quickstart.md)

**Tests**: Focused unit tests are included because exactly-once saving and durable, unchanged match values are required behaviour.

## Phase 1: Setup

**Purpose**: Add the approved local persistence dependency and verify ignore rules remain appropriate.

- [X] T001 Add the Room runtime and compiler dependencies in `app/build.gradle.kts`.
- [X] T002 Verify `.gitignore` covers Android and Kotlin generated output in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Create the smallest approved Room data boundary and make it available through a manual application container.

- [X] T003 Add the completed-local-match Room entity and required type converters in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchEntity.kt`.
- [X] T004 Add the insert and test-read DAO contract in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchDao.kt`.
- [X] T005 Add the Room database in `app/src/main/java/com/dtbuddy/app/data/AppDatabase.kt`.
- [X] T006 Add a repository that maps a complete temporary selection into a completed local match in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.
- [X] T007 Add the manual application container and database lifecycle in `app/src/main/java/com/dtbuddy/app/DTBuddyApplication.kt` and register it in `app/src/main/AndroidManifest.xml`.

**Checkpoint**: The data boundary can save one complete local match without UI changes.

## Phase 3: User Story 1 - Save a Completed Match (Priority: P1)

**Goal**: Let the player save the five chosen values once, see a clear confirmation, and begin another fresh log.

**Independent Test**: Complete the guided choices, tap Save match once, confirm local success, then start another log with empty temporary choices.

### Tests for User Story 1

- [X] T008 [US1] Add repository tests for saved values and one insert per save action in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T009 [US1] Add ViewModel tests for complete-save validation, duplicate-save prevention, and clearing temporary choices in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T010 [US1] Add complete-selection validation, save coordination, and a fresh-log action in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T011 [US1] Supply the repository from the application container in `app/src/main/java/com/dtbuddy/app/MainActivity.kt`.
- [X] T012 [US1] Add Save match, duplicate-save prevention, the saved confirmation destination, and Log another match action in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: The player can save one completed unlinked local match and restart the guided flow; no history or statistics UI exists.

## Phase 4: Validation

**Purpose**: Check the approved scope and record the validation result.

- [X] T013 Run `gradlew.bat testDebugUnitTest` and record the result in `specs/005-save-local-match/quickstart.md`.
- [X] T014 Run the manual scenarios in `specs/005-save-local-match/quickstart.md` and record the emulator result in that file.
- [X] T015 Review `specs/005-save-local-match/spec.md` against the completed app and confirm that history, statistics, editing, deletion, notes, favourites, accounts, linked opponents, and online features were not added.

## Dependencies & Execution Order

- T001 and T002 precede T003 through T007.
- T003 through T007 precede T008 through T012.
- T008 and T009 precede their related implementation tasks.
- T010 through T012 precede validation.

## Parallel Opportunities

- T002 can run alongside T001.
- T008 and T009 use different test files and can run in parallel after the foundational data boundary is present.

## Implementation Strategy

1. Add only the approved Room storage and its small repository boundary.
2. Test the new data boundary and flow actions before wiring the UI.
3. Add the Save match and confirmation user flow.
4. Verify automatically and manually, then confirm the excluded features remain absent.
