# Implementation Plan: Main Navigation

**Branch**: `codex/navigation-shell` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

## Summary

Add the confirmed four-destination main navigation around the existing local match-log flow. Log match remains the initial destination. Profile provides the permanent entry point to existing local match history; Requests and Global stats are clear offline placeholders only. The current data model, repository, and ViewModel are reused unchanged.

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Language/Version**: Kotlin with Java 17 toolchain

**Primary Dependencies**: Jetpack Compose, Material 3, Navigation Compose

**Storage**: Existing Room/SQLite completed-match data, read unchanged by the existing history screen

**Testing**: Existing JUnit local unit tests, Android debug compilation, and focused manual Android validation

**Target Platform**: Android 8.0 and later (API 26+)

**Project Type**: Android mobile app

**Performance Goals**: Main destinations switch immediately during normal solo use.

**Constraints**: Fully local and offline; use the approved four destinations; no new dependency, storage, architecture layer, data collection, account, request, statistics, or online capability.

**Scale/Scope**: One navigation shell, two unavailable-feature messages, one minimal local Profile screen, and an existing-history entry point.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- Source documents remain the authority and the approved scope is fully reflected: **pass**.
- The work is one small Milestone 1 offline increment: **pass**.
- Existing Kotlin, Compose, Navigation Compose, Room, repository, and ViewModel structure is reused without a new dependency or layer: **pass**.
- No new data, identity, credentials, logging, network, or external asset is introduced: **pass**.
- Automated build/unit checks and manual Android validation are planned: **pass**.

The post-design check remains **pass**: design only adds navigation UI and local entry points to already-existing functionality.

## Project Structure

### Documentation (this feature)

```text
specs/007-main-navigation/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/main-navigation-ui.md
├── quickstart.md
└── tasks.md
```

### Source Code (repository root)
<!--
  ACTION REQUIRED: Replace the placeholder tree below with the concrete layout
  for this feature. Delete unused options and expand the chosen structure with
  real paths (e.g., apps/admin, packages/something). The delivered plan must
  not include Option labels.
-->

```text
app/src/main/java/com/dtbuddy/app/
├── MainActivity.kt                         # existing application entry point
└── heroes/
    └── HeroSelectionScreen.kt              # main navigation shell and existing guided flow

app/src/test/java/com/dtbuddy/app/
└── heroes/                                 # existing flow and ViewModel coverage

specs/007-main-navigation/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/main-navigation-ui.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Extend the existing single Compose screen file because this small feature is presentation-only. The existing ViewModel continues to own match state, and the existing history destination continues to load saved records.
