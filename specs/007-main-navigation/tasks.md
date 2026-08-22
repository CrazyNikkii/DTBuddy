# Tasks: Main Navigation

**Input**: Design documents from `/specs/007-main-navigation/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/main-navigation-ui.md](contracts/main-navigation-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Existing local unit tests and Android debug compilation cover the preserved flow. Manual Android scenarios validate the navigation-specific user experience; no new data or ViewModel behaviour requires a new unit test.

## Phase 1: Setup

**Purpose**: Confirm the current project can support the approved presentation-only increment without setup changes.

- [X] T001 Verify `.gitignore` remains appropriate for the existing Kotlin/Android project in `.gitignore`.

## Phase 2: Foundational

**Purpose**: Establish the single navigation boundary used by the approved destinations.

- [X] T002 Add the four-destination main navigation shell and initial Log match selection in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: Every confirmed main destination is reachable while the existing guided flow remains available under Log match.

## Phase 3: User Story 1 - Navigate the Solo App (Priority: P1)

**Goal**: Let a player use the approved four-destination app structure during the offline solo test.

**Independent Test**: Launch the app, switch among all four destinations, and confirm that Log match is selected initially while Requests and Global stats provide only their approved unavailable-feature messages.

### Implementation for User Story 1

- [X] T003 [US1] Preserve the guided match-log routes within Log match and add the Requests and Global stats offline placeholder screens in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: A player can visit the required four destinations without saving a partial log or reaching any future-milestone feature.

## Phase 4: User Story 2 - Reopen Local History (Priority: P1)

**Goal**: Let a player return to their saved local history from the new Profile destination.

**Independent Test**: Save a match, relaunch the app, select Profile then Match history, and use back to return to Profile.

### Implementation for User Story 2

- [X] T004 [US2] Add the minimal local Profile screen and route its Match history action through the existing history destination with correct back behaviour in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.

**Checkpoint**: Previously saved records are accessible from Profile after app relaunch without any profile, statistics, or online feature.

## Phase 5: Validation

**Purpose**: Verify the approved scope and record outcomes.

- [X] T005 Run `.\gradlew.bat testDebugUnitTest` and record the result in `specs/007-main-navigation/quickstart.md`.
- [X] T006 Run `.\gradlew.bat assembleDebug` and record the result in `specs/007-main-navigation/quickstart.md`.
- [X] T007 Run the manual Android navigation scenarios in `specs/007-main-navigation/quickstart.md` and record the result in `specs/007-main-navigation/quickstart.md`.
- [X] T008 Review `specs/007-main-navigation/spec.md` against the completed app and confirm that statistics, profile data, accounts, requests, badges, notes, favourites, match editing, match deletion, and online features were not added.

## Dependencies & Execution Order

- T001 precedes T002.
- T002 precedes T003 and T004.
- T003 and T004 are sequential because they extend the same screen file.
- T005 through T008 follow T004.

## Parallel Opportunities

- No code tasks are marked parallel: this deliberately small feature changes one Compose screen file.

## Implementation Strategy

1. Verify the existing project setup needs no new dependency or ignore rule.
2. Wrap the existing guided flow in the required persistent main navigation.
3. Add the two unavailable-feature messages and minimal Profile-to-history path.
4. Run the automated checks and manual navigation scenarios, then confirm excluded scope remains absent.

## Phase 6: Review Remediation

**Purpose**: Correct the must-fix findings from independent review and revalidate the affected Android behaviour.

- [X] T009 Return Profile → Match history to Profile when the Android system Back action is used in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T010 Keep main-destination selection transient so Log match is selected after Android activity or process restoration in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T011 Deliberately ignore the local tooling artifact in `.gitignore`.
- [X] T012 Re-run the affected manual Android scenarios: system Back from Profile history and app restoration after selecting another tab; record the results in `specs/007-main-navigation/quickstart.md`.
- [X] T013 Re-run `.\gradlew.bat testDebugUnitTest` and `.\gradlew.bat assembleDebug` after review remediation; record the results in `specs/007-main-navigation/quickstart.md`.
