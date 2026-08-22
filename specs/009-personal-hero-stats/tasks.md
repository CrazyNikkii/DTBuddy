# Tasks: Personal Hero Statistics List

**Input**: Design documents from `/specs/009-personal-hero-stats/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/personal-hero-stats-ui.md](contracts/personal-hero-stats-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused local unit tests are included because correct hero-specific calculation, ordering, and opponent-only exclusion are required.

## Phase 1: Setup

**Purpose**: Confirm the current project setup supports this local-only increment.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define the derived hero record and calculate it from the underlying completed matches.

- [X] T002 Add the derived personal-hero-record value type and repository calculation in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.
- [X] T003 Expose hero-record loading and screen state from the existing ViewModel in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.

**Checkpoint**: The app has one local, derived source for every played hero's games, wins, losses, and win rate.

## Phase 3: User Story 1 - View Personal Hero Records (Priority: P1)

**Goal**: Let a solo player open Heroes from Profile and see one record per hero they have played.

**Independent Test**: Save a known mix of wins and losses with at least three player heroes, open Heroes from Profile, and compare every row with the saved match history.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for empty, all-win, all-loss, mixed, alphabetical, and opponent-only-hero cases in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel coverage proving loaded hero records reach screen state in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T006 [US1] Add the Profile Heroes action and render the empty and populated hero-record list, preserving the existing Profile summary and Match history action, in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: Profile provides an accurate device-local Heroes list without exposing detailed or online statistics.

## Phase 4: Validation

**Purpose**: Verify the approved scope and record outcomes.

- [X] T007 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and record the result in `specs/009-personal-hero-stats/quickstart.md`.
- [X] T008 Run `.\gradlew.bat assembleDebug --no-daemon` and record the result in `specs/009-personal-hero-stats/quickstart.md`.
- [X] T009 Run the manual Android scenarios in `specs/009-personal-hero-stats/quickstart.md` and record the result in `specs/009-personal-hero-stats/quickstart.md`.
- [X] T010 Review `specs/009-personal-hero-stats/spec.md` against the completed app and confirm excluded hero detail, matchup, turn-order, charts, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, and online/global/community features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T006.
- T003 precedes T005 and T006.
- T004 and T005 can proceed in parallel after their relevant code exists; implementation work remains sequential because it extends shared files.
- T007 through T010 follow T006.

## Parallel Opportunities

- T004 and T005 modify different test files and can run in parallel after the hero-record calculation and ViewModel state exist.

## Implementation Strategy

1. Confirm the existing ignore rules remain sufficient.
2. Calculate records directly from completed local matches and expose them through the current ViewModel state.
3. Test the calculation and state loading, then add the Profile action and Heroes display.
4. Run automated and manual checks, and confirm that only the approved personal hero list was added.
