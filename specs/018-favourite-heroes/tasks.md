# Tasks: Favourite Heroes

**Input**: Design documents from `/specs/018-favourite-heroes/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/favourite-hero-ui.md](contracts/favourite-hero-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Repository, ViewModel, and Room migration tests are required to prove ordering, the three-hero limit, safe upgrade, and unchanged match statistics.

## Phase 1: Setup

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

- [X] T002 Add `FavouriteHeroEntity`, `FavouriteHeroDao`, and the explicit Room version-2-to-version-3 migration in `app/src/main/java/com/dtbuddy/app/data/` and `app/src/main/java/com/dtbuddy/app/data/AppDatabase.kt`.
- [X] T003 Expose the favourite DAO through the existing application container in `app/src/main/java/com/dtbuddy/app/DTBuddyApplication.kt` and extend `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt` with validated ordered favourite operations.

**Checkpoint**: Existing installations safely gain an empty local favourites collection.

## Phase 3: User Story 1 - Choose favourite heroes (Priority: P1)

**Goal**: Let the player fill or replace up to three local favourite-hero slots from Profile.

**Independent Test**: Fill three slots, replace a selected slot, attempt a duplicate, then reopen the screen and confirm the saved list.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for sequential slot selection, replacement, duplicate/unknown/limit rejection, and unchanged match statistics in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel tests for loading and changing favourites in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.
- [X] T006 [P] [US1] Add Room migration coverage from version 2 to version 3 in `app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt`.

### Implementation for User Story 1

- [X] T007 [US1] Add favourite list state and repository-coordinating ViewModel actions in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T008 [US1] Add Profile entry and local favourite-management UI, including fixed-slot browse, search, and replacement controls, in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 4: User Story 2 - Select a favourite while logging (Priority: P1)

**Goal**: Let the player select a saved favourite only from their own hero picker.

**Independent Test**: Save ordered favourites, begin a match, select one for the player, and confirm the opponent picker does not show favourites.

### Implementation for User Story 2

- [X] T009 [US2] Load local favourites for the player-hero picker and render the ordered conditional Favourites section alongside existing browse/search UI in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 5: Validation

- [X] T010 Run `.\gradlew.bat testDebugUnitTest --no-daemon`, `.\gradlew.bat assembleDebug --no-daemon`, and `.\gradlew.bat assembleDebugAndroidTest --no-daemon`; record results in `specs/018-favourite-heroes/quickstart.md`.
- [X] T011 Run the manual scenarios in `specs/018-favourite-heroes/quickstart.md` and record the result in `specs/018-favourite-heroes/quickstart.md`.
- [X] T012 Confirm `specs/018-favourite-heroes/spec.md` matches the implementation and that accounts, owned heroes, requests, global statistics, sync, and public features were not added.
- [X] T013 Validate the fixed-slot setup and player-only picker behaviour from `specs/018-favourite-heroes/quickstart.md`.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T009.
- T003 precedes all story tests and implementation.
- T004 through T006 can run in parallel after T003.
- T007 follows the test design; T008 follows T007; T009 follows T008.
- T010 through T012 follow T009.

## Parallel Opportunities

- T004, T005, and T006 modify separate test files.

## Implementation Strategy

1. Safely add the local ordered data with an explicit migration.
2. Test and implement favourite management from Profile.
3. Show the saved list only in the player's existing hero picker.
4. Run automated and manual validation, then confirm the feature remains local-only.
