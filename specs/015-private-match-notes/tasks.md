# Tasks: Private Match Notes

**Input**: Design documents from `/specs/015-private-match-notes/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/private-match-notes-ui.md](contracts/private-match-notes-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Repository and ViewModel unit tests are required to prove notes stay with one selected match, do not affect statistics, and are not stored by abandoned edits. Room test compilation verifies the changed database interface.

## Phase 1: Setup

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

- [X] T002 Add the nullable note field and an explicit version-1-to-version-2 Room migration in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchEntity.kt` and `app/src/main/java/com/dtbuddy/app/data/AppDatabase.kt`.
- [X] T003 Thread the nullable note through insert and selected-row update operations in `app/src/main/java/com/dtbuddy/app/data/CompletedMatchDao.kt` and `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.

**Checkpoint**: Existing matches migrate to no-note records, while newly saved or updated records can carry a note.

## Phase 3: User Story 1 - Save and Revisit a Private Match Note (Priority: P1) 🎯 MVP

**Goal**: Let a solo player optionally save a short private note with a local match and read it in Match history.

**Independent Test**: Save one match with a note and one without, then confirm history distinguishes them and derived statistics are identical to the same note-free matches.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository coverage for saving notes, normalising blank input, preserving history order, and unchanged statistics in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel state coverage for retaining a new-match note through review and save in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.
- [X] T006 [P] [US1] Extend stored-row coverage for note insert and retrieval in `app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt`.

### Implementation for User Story 1

- [X] T007 [US1] Add draft-only note state, 500-character input normalisation, and note-aware save creation in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T008 [US1] Add the optional private-note field to new-match review and conditional note display to Match history in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A note can be saved privately with a new match and revisited in local history.

## Phase 4: User Story 2 - Correct or Remove a Private Match Note (Priority: P2)

**Goal**: Let a player add, replace, or clear one selected local match's note through the existing edit overview.

**Independent Test**: Update one saved note, then abandon a different draft change and confirm only the saved selected note changed.

### Tests for User Story 2

- [X] T009 [P] [US2] Add repository coverage for replacing and clearing only the selected note while preserving all match values and statistics in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T010 [P] [US2] Add ViewModel coverage for prefilled note edits, saved changes, and abandoned edits in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 2

- [X] T011 [US2] Prefill the edit draft's note and update only the selected local match on Save changes in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T012 [US2] Add the editable private-note field to the existing edit overview in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can safely correct or remove a private note without changing other saved match data.

## Phase 5: Validation

- [X] T013 Run `.\gradlew.bat testDebugUnitTest --no-daemon`, `.\gradlew.bat assembleDebug --no-daemon`, and `.\gradlew.bat assembleDebugAndroidTest --no-daemon`; record results in `specs/015-private-match-notes/quickstart.md`.
- [X] T014 Run the manual Android scenarios in `specs/015-private-match-notes/quickstart.md` and record the result in `specs/015-private-match-notes/quickstart.md`.
- [X] T015 Confirm `specs/015-private-match-notes/spec.md` matches the implementation and that notes, favourites, accounts, linked opponents, requests, global statistics, cloud sync, and public profiles were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T012.
- T003 precedes all story tests and implementation.
- T004 through T006 can run in parallel after T003; T007 and T008 follow their relevant test design.
- T009 and T010 can run in parallel after T007; T011 and T012 follow sequentially in their shared files.
- T013 through T015 follow T012.

## Parallel Opportunities

- T004, T005, and T006 modify separate test files.
- T009 and T010 modify separate test files.

## Implementation Strategy

1. Preserve existing local records with a single nullable field and explicit migration.
2. Test and implement new-match note save and history display.
3. Test and implement prefilled note editing and clearing.
4. Run automated and manual validation, then confirm the feature remains within the approved local-only scope.
