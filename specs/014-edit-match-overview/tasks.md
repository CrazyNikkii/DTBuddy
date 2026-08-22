# Tasks: Edit Match Overview

**Input**: Design documents from `/specs/014-edit-match-overview/`

## Phase 1: Setup

- [X] T001 Verify `.gitignore` remains appropriate for this existing Android project in `.gitignore`.

## Phase 2: User Story 1 - Correct One Detail from an Overview (P1)

**Goal**: Let a player change exactly one saved match value from a clickable overview.

- [X] T002 [US1] Add state tests proving edit selections preserve unrelated values in `app/src/test/java/com/dtbuddy/app/heroes/MatchHeroSelectionViewModelTest.kt`.
- [X] T003 [US1] Preserve unrelated values when editing while retaining normal guided-log resets in `app/src/main/java/com/dtbuddy/app/heroes/MatchHeroSelectionState.kt`.
- [X] T004 [US1] Add an edit-overview route, five actionable value rows, single-choice return paths, and overview save in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

## Phase 3: Validation

- [X] T005 Run `.\gradlew.bat testDebugUnitTest --no-daemon` and `.\gradlew.bat assembleDebug --no-daemon`; record results in `specs/014-edit-match-overview/quickstart.md`.
- [X] T006 Run the manual overview scenario in `specs/014-edit-match-overview/quickstart.md` and record the result.
- [X] T007 Confirm the feature adds no new data, online, or future-milestone capability.
