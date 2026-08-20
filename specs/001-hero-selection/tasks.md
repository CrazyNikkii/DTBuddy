# Tasks: Hero Selection

**Input**: Design documents from `/specs/001-hero-selection/`

**Prerequisites**: [plan.md](plan.md), [spec.md](spec.md), [research.md](research.md), [data-model.md](data-model.md), [contracts/hero-selector-ui.md](contracts/hero-selector-ui.md), and [quickstart.md](quickstart.md)

**Tests**: Catalog/search unit tests are included because the fixed 45-hero roster is a product requirement and must not silently change.

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Create the standard Android project that Android Studio can open and run.

- [X] T001 Create the Android Gradle project configuration in `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`, `gradle/wrapper/gradle-wrapper.properties`, and `app/build.gradle.kts`.
- [X] T002 Create the Android application metadata and original text-only resources in `app/src/main/AndroidManifest.xml`, `app/src/main/res/values/strings.xml`, and `app/src/main/res/values/themes.xml`.
- [X] T003 Update Kotlin and Android build exclusions in `.gitignore`.

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Define the single canonical hero catalog used by the screen and its checks.

- [X] T004 Create the `Hero` and `HeroGroup` definitions in `app/src/main/java/com/dtbuddy/app/heroes/Hero.kt`.
- [X] T005 Create the complete frozen 45-hero catalog and trimmed, case-insensitive search function in `app/src/main/java/com/dtbuddy/app/heroes/HeroCatalog.kt`.
- [X] T006 Create catalog/search unit tests in `app/src/test/java/com/dtbuddy/app/heroes/HeroCatalogTest.kt`.

**Checkpoint**: The canonical catalog rules are protected before the screen is built.

---

## Phase 3: User Story 1 - Browse and Search Heroes (Priority: P1) 🎯 MVP

**Goal**: Let a player browse the four groups and search the entire roster from the first screen.

**Independent Test**: Run the app in an Android emulator, browse every group, search `vampire`, search `  MILES  `, search `zzz`, then clear the search.

- [X] T007 [US1] Create the grouped browse, full-roster search, and no-results UI in `app/src/main/java/com/dtbuddy/app/heroes/HeroSelectionScreen.kt`.
- [X] T008 [US1] Create the application entry activity and connect it to the hero-selection screen in `app/src/main/java/com/dtbuddy/app/MainActivity.kt`.

**Checkpoint**: The feature works independently on an emulator without an account or internet connection.

---

## Phase 4: Polish & Validation

**Purpose**: Verify the agreed small slice and retain no generated build output.

- [X] T009 Run the catalog unit tests with `gradlew.bat testDebugUnitTest`.
- [X] T010 Run the emulator scenarios in `specs/001-hero-selection/quickstart.md` and record the result in `specs/001-hero-selection/quickstart.md`.
- [X] T011 Review every requirement in `specs/001-hero-selection/spec.md` against the completed app and mark completed tasks in this file.

## Dependencies & Execution Order

- Phase 1 must finish before Phase 2.
- Phase 2 must finish before User Story 1.
- User Story 1 must finish before validation.
- This is one user story; there are no useful parallel work streams in this small feature.

## Implementation Strategy

1. Establish a project Android Studio can import.
2. Protect the frozen roster and search behaviour with unit tests.
3. Implement the single screen and launch it.
4. Run the automated and emulator checks listed above.
