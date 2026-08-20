# Implementation Plan: Select Match Heroes

**Branch**: `codex/select-match-heroes` | **Date**: 2026-08-20 | **Spec**: [spec.md](spec.md)

## Summary

Convert the current browse-and-search screen into a reusable hero picker. A small ViewModel holds the two choices while the screen is active, advances after the first selection, and confirms the pair after the second. No persistence is introduced.

## Technical Context

**Language/Version**: Kotlin on the existing Android project

**Primary Dependencies**: Existing Jetpack Compose, Material 3, and Navigation Compose

**Storage**: None for this slice; choices are temporary ViewModel state

**Testing**: Existing JUnit unit-test setup; add a focused unit test for flow state

**Target Platform**: Android 8.0 and later

**Project Type**: Offline Android mobile app

**Performance Goals**: Hero results update immediately while typing and selection advances on one tap

**Constraints**: Navigation Compose is the only dependency added because the source-of-truth Android structure requires it; no backend, account, persistence, or completed-match data

**Scale/Scope**: One two-step in-memory flow using the frozen 45-hero roster

## Constitution Check

| Rule | Result | Evidence |
|---|---|---|
| Source documents take priority | Pass | Scope is limited to the confirmed guided hero choices. |
| One small approved piece | Pass | No match fields, persistence, or later features are included. |
| Milestone 1 remains local/offline | Pass | No network or account behavior is added. |
| Simple proportionate design | Pass | Existing Compose and hero catalog are reused; Navigation Compose is added only because the source documents explicitly require it. |
| Suitable checks | Pass | Flow-state and existing roster/search unit tests plus manual quickstart checks are planned. |

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/
├── MainActivity.kt
└── heroes/
    ├── Hero.kt
    ├── HeroCatalog.kt
    ├── HeroSelectionScreen.kt
    ├── MatchHeroSelectionState.kt
    └── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/heroes/
├── HeroCatalogTest.kt
└── MatchHeroSelectionStateTest.kt

specs/002-select-match-heroes/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/hero-selection-flow.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Keep the small flow with the existing hero feature. Navigation Compose owns the two destinations and Android Back behavior, while a small ViewModel retains the temporary hero choices.

## Complexity Tracking

No constitution violations or added complexity require tracking.
