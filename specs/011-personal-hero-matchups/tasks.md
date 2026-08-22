# Tasks: Personal Hero Matchup Table

**Input**: Design documents from `/specs/011-personal-hero-matchups/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/personal-hero-matchups-ui.md](contracts/personal-hero-matchups-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused local unit tests are included because correct grouping and non-overlapping match contributions are required.

## Phase 1: Setup

**Purpose**: Confirm the existing Android setup supports this local-only increment.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define and derive opponent-specific records from underlying saved matches.

- [X] T002 Add a derived matchup-record type and include alphabetically ordered matchup records in the existing selected-hero detail calculation in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.
- [X] T003 Expose matchup-inclusive selected-hero detail through the existing ViewModel state in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.

**Checkpoint**: The app has one local derived source for the selected hero's overall, turn-order, and matchup records.

## Phase 3: User Story 1 - View a Hero's Matchup Record (Priority: P1)

**Goal**: Let a solo player see their selected hero's local record against every opponent hero they have faced.

**Independent Test**: Save known results for one player hero against two opponent heroes, open that hero, and compare every matchup row with saved history.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for no matchup rows, all wins, all losses, mixed results, rounding, alphabetic ordering, and exact one-row contribution per selected-hero match in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel coverage proving a matchup-inclusive selected-hero detail reaches screen state in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T006 [US1] Render the labelled Matchups section and its opponent hero rows in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can inspect local matchup records from any played hero detail without reaching a future feature.

## Phase 4: Validation

**Purpose**: Verify the approved scope and match-record outcomes.

- [X] T007 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and record the result in `specs/011-personal-hero-matchups/quickstart.md`.
- [X] T008 Run `.\gradlew.bat assembleDebug --no-daemon` and record the result in `specs/011-personal-hero-matchups/quickstart.md`.
- [X] T009 Run the manual Android scenarios in `specs/011-personal-hero-matchups/quickstart.md` and record the result in `specs/011-personal-hero-matchups/quickstart.md`.
- [X] T010 Review `specs/011-personal-hero-matchups/spec.md` against the completed app and confirm excluded global/community statistics, charts, sorting, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T006.
- T003 precedes T005 and T006.
- T004 and T005 can proceed in parallel after the selected-hero calculation and ViewModel state exist; implementation work remains sequential because it extends shared files.
- T007 through T010 follow T006.

## Parallel Opportunities

- T004 and T005 modify different test files and can run in parallel after the selected-hero calculation and ViewModel state exist.

## Implementation Strategy

1. Confirm the existing ignore rules remain sufficient.
2. Calculate matchup rows directly from saved local matches and expose them through the existing selected-hero detail.
3. Test the calculation and state loading, then render the table on the current hero detail page.
4. Run automated and manual checks, then confirm the change stayed within the approved matchup-table scope.
