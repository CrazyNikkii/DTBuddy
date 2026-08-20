# Tasks: Select Match Heroes

**Input**: Design documents from `/specs/002-select-match-heroes/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/hero-selection-flow.md](contracts/hero-selection-flow.md), and [quickstart.md](quickstart.md)

**Tests**: A focused flow-state unit test is included alongside the existing catalog checks because retaining the first selection when going back is a required behavior.

## Phase 1: Setup

**Purpose**: Confirm the existing Android project and ignore rules support this small feature.

- [X] T001 Verify existing Kotlin/Compose project and `.gitignore` in `app/build.gradle.kts` and `.gitignore`.

## Phase 2: Foundational

**Purpose**: Define the temporary state shared by the two picker steps.

- [X] T002 Create flow-state model and ViewModel in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt` and `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModel.kt`.
- [X] T003 Create flow-state unit tests in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionStateTest.kt`.

## Phase 3: User Story 1 - Choose Both Match Heroes (Priority: P1)

**Goal**: Choose a logging-player hero, choose an opponent hero, and correct the first choice with Back.

**Independent Test**: Run the app, select a hero using search, select an opponent, then return and change the first hero.

- [X] T004 [US1] Use Navigation Compose for the player, opponent, and confirmation destinations, including safe restoration when temporary choices are absent, in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T005 [US1] Wire the temporary ViewModel state to the app entry screen in `app/src/main/java/com/dtbuddy/app/MainActivity.kt` and `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T006 [US1] Show the two-hero confirmation state in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 4: Validation

**Purpose**: Verify the specified behavior and retain only the approved scope.

- [X] T007 Run unit tests from `app/src/test/java/com/dtbuddy/app/heroes/` with `gradlew.bat testDebugUnitTest` using the locally cached Java 25 runtime after adding Navigation Compose.
- [X] T008 Run the manual scenarios in `specs/002-select-match-heroes/quickstart.md`, including Android system Back and interrupted-flow reopening, and record the product-owner result in that file.
- [X] T009 Review `specs/002-select-match-heroes/spec.md` against the completed app and confirm no later match-log fields were added.

## Dependencies & Execution Order

- T001 precedes T002 and T003.
- T002 and T003 precede T004 through T006.
- T004 through T006 precede validation.

## Implementation Strategy

1. Add a tiny, testable in-memory state model.
2. Reuse the existing picker for the two approved roles.
3. Confirm the pair without saving anything.
4. Run automated and manual checks.
