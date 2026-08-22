# Implementation Plan: Edit Match Overview

**Branch**: `codex/013-edit-local-match` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

## Summary

Replace the full guided edit route with a Compose overview route. It displays five actionable current values, opens one existing selection screen at a time, returns to the overview after a replacement, and reuses the existing update/save path.

## Technical Context

**Language/Platform**: Kotlin, Jetpack Compose Android app (API 26+).

**Storage**: Existing Room database; no schema or repository change.

**Testing**: Existing ViewModel unit tests, debug build, and manual emulator scenarios.

**Constraints**: Offline only; no dependency or new architecture layer; preserve the normal guided log flow.

## Constitution Check

Pass. This is one user-approved refinement of Milestone 1 local match editing, with no new data, network, or future-milestone capability.

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/heroes/
├── HeroSelectionScreen.kt
└── MatchHeroSelectionState.kt

app/src/test/java/com/dtbuddy/app/heroes/
└── MatchHeroSelectionViewModelTest.kt
```

## Design Decisions

- Editing state preserves unrelated values when one value is changed; normal logging keeps its current reset behaviour.
- The overview owns the edit save action and returns to the existing saved confirmation.
