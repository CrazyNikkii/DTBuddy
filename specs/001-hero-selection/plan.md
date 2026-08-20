# Implementation Plan: Hero Selection

**Branch**: `001-hero-selection` | **Date**: 2026-08-20 | **Spec**: [spec.md](spec.md)

**Input**: Approved feature specification in [spec.md](spec.md)

## Summary

Create the first Android application module and one offline hero-selection screen. The screen reads a fixed, source-controlled 45-hero catalog, supports grouped browsing and name search, and has automated catalog checks. It intentionally contains no selection result, navigation, persistence, accounts, or networking.

## Technical Context

**Language/Version**: Kotlin, current stable version bundled/configured by Android Studio

**Primary Dependencies**: Jetpack Compose and Material 3 from the standard Android Studio Empty Activity setup

**Storage**: None in this feature

**Testing**: Kotlin unit tests for the static catalog and search behaviour; manual emulator test using [quickstart.md](quickstart.md)

**Target Platform**: Android 8.0 (API 26) and newer

**Project Type**: Single Android mobile application

**Performance Goals**: The local 45-item catalog appears immediately after the screen opens and search updates while typing.

**Constraints**: Fully offline; API 26 minimum; original text-only visuals; no extra architecture layers or libraries beyond the small Compose application need.

**Scale/Scope**: One app module, one screen, four browse groups, and 45 canonical hero entries.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

| Gate | Result | Evidence |
|------|--------|----------|
| Source documents remain authoritative | Pass | The scope and roster come directly from PROJECT.md and the technology/minimum Android version come from TECHNICAL_CONSTRAINTS.md. |
| One small approved piece only | Pass | The feature stops at browsing and searching heroes. |
| Milestone 1 remains local/offline | Pass | The catalog is bundled with the app; no account, service, or network work is included. |
| Simple approved architecture | Pass | One activity and composable UI with a small static catalog; no repository or ViewModel is needed until a concrete data/action need exists. |
| Suitable checks are included | Pass | Unit tests protect roster/search rules and an emulator guide checks the visible screen. |

## Project Structure

### Documentation (this feature)

```text
specs/001-hero-selection/
├── plan.md              # This file ($speckit-plan command output)
├── research.md          # Phase 0 output ($speckit-plan command)
├── data-model.md        # Phase 1 output ($speckit-plan command)
├── quickstart.md        # Phase 1 output ($speckit-plan command)
├── contracts/           # Phase 1 output ($speckit-plan command)
└── tasks.md             # Phase 2 output ($speckit-tasks command - NOT created by $speckit-plan)
```

### Source Code (repository root)
```text
app/
├── src/main/
│   ├── AndroidManifest.xml
│   └── java/com/dtbuddy/app/
│       ├── MainActivity.kt
│       └── heroes/
│           ├── Hero.kt
│           ├── HeroCatalog.kt
│           └── HeroSelectionScreen.kt
└── src/test/java/com/dtbuddy/app/heroes/
    └── HeroCatalogTest.kt
```

**Structure Decision**: Use the standard single-module Android layout. Keep the fixed catalog and its screen in a small `heroes` package. This is enough for the approved display-only feature; Room, repositories, ViewModels, and navigation follow only when a later approved feature needs them.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
