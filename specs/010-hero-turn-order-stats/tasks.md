# Tasks: Personal Hero Turn-Order Detail

**Input**: Design documents from `/specs/010-hero-turn-order-stats/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/personal-hero-turn-order-ui.md](contracts/personal-hero-turn-order-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Focused local unit tests are included because correct first-player and second-player partitioning is required.

## Phase 1: Setup

**Purpose**: Confirm the existing Android setup supports this local-only increment.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define and derive the selected hero's three records from underlying saved matches.

- [X] T002 Add derived selected-hero and turn-order record types plus repository calculation in `app/src/main/java/com/dtbuddy/app/data/LocalMatchRepository.kt`.
- [X] T003 Expose selected-hero-detail loading and screen state from the existing ViewModel in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.

**Checkpoint**: The app has one local derived source for the selected hero's overall, first-player, and second-player records.

## Phase 3: User Story 1 - View a Hero's Turn-Order Record (Priority: P1)

**Goal**: Let a solo player open a played hero and understand their results under both turn orders.

**Independent Test**: Save known wins and losses for one player hero under both turn orders, open it from Profile's Heroes list, and compare all values with saved history.

### Tests for User Story 1

- [X] T004 [P] [US1] Add repository tests for empty turn-order sections, all wins, all losses, mixed records, and complete first-player/second-player partitioning in `app/src/test/java/com/dtbuddy/app/data/LocalMatchRepositoryTest.kt`.
- [X] T005 [P] [US1] Add ViewModel coverage proving a loaded selected-hero detail reaches screen state in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.

### Implementation for User Story 1

- [X] T006 [US1] Make personal hero rows open detail and render the three labelled record sections with correct Back behaviour in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can inspect accurate local turn-order results for any played hero without reaching a future feature.

## Phase 4: Validation

**Purpose**: Verify the approved scope and record outcomes.

- [X] T007 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and record the result in `specs/010-hero-turn-order-stats/quickstart.md`.
- [X] T008 Run `.\gradlew.bat assembleDebug --no-daemon` and record the result in `specs/010-hero-turn-order-stats/quickstart.md`.
- [X] T009 Run the manual Android scenarios in `specs/010-hero-turn-order-stats/quickstart.md` and record the result in `specs/010-hero-turn-order-stats/quickstart.md`.
- [X] T010 Review `specs/010-hero-turn-order-stats/spec.md` against the completed app and confirm excluded matchup, charts, sorting, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, global/community statistics, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 through T006.
- T003 precedes T005 and T006.
- T004 and T005 can proceed in parallel after their related code is available; implementation work remains sequential because it extends shared files.
- T007 through T010 follow T006.

## Parallel Opportunities

- T004 and T005 modify different test files and can run in parallel after the selected-hero calculation and ViewModel state exist.

## Implementation Strategy

1. Confirm the existing ignore rules remain sufficient.
2. Calculate the three records directly from saved local matches and expose them through the existing ViewModel.
3. Test the calculation and state loading, then let an existing hero row open the new detail page.
4. Run automated and manual checks, then confirm the change stayed within the approved turn-order-detail scope.
