# Implementation Plan: Edit Back Navigation

**Branch**: `codex/private-match-notes` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

## Summary

Extend the existing Compose navigation state so Back from an edit opens a discard dialog. Discarding clears only the transient edit draft and returns the user to Match history; cancelling preserves the draft.

## Technical Context

- Kotlin and Jetpack Compose Android app, API 26+.
- Existing Navigation Compose, ViewModel, and Room database.
- No schema, repository, dependency, or network change.

## Constitution Check

Pass. This is a single local usability fix for the approved edit flow.

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/heroes/
├── HeroSelectionScreen.kt
├── MatchHeroSelectionState.kt
└── MatchHeroSelectionViewModel.kt

app/src/test/java/com/dtbuddy/app/heroes/
└── MatchHeroSelectionViewModelTest.kt
```

## Design Decision

Use a draft-discard ViewModel action rather than saving or deleting any database record. Keep the existing Match history UI as the return destination.
