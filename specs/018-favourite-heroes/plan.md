# Implementation Plan: Favourite Heroes

**Branch**: `codex/favourite-heroes` | **Date**: 2026-08-22 | **Spec**: [spec.md](spec.md)

## Summary

Add three private, device-local favourite-hero slots. The player fills or replaces them in order from Profile, and only the player's hero picker displays the list as a Favourites section.

## Technical Context

**Language/Version**: Kotlin on Android, Java 17 toolchain

**Primary Dependencies**: Jetpack Compose, Navigation Compose, Room

**Storage**: Existing private on-device Room/SQLite database; add a favourite-hero table and an explicit version-2-to-version-3 migration

**Testing**: JUnit unit tests, Room Android instrumentation tests, Gradle debug build

**Target Platform**: Android 8.0+ (API 26+)

**Project Type**: Android mobile app

**Performance Goals**: The three-item favourites list is available before the player's hero picker is displayed.

**Constraints**: Fully offline; no new dependencies or architectural layers; retain existing match and note data; preserve the frozen 45-hero roster.

**Scale/Scope**: One device-local owner and at most three favourite heroes.

## Constitution Check

**Pre-design: PASS.** The feature is an approved, small Milestone 1 increment. It uses the mandated Kotlin, Compose, and Room stack; adds no online, account, or public capability; and does not modify either source-of-truth document.

**Post-design: PASS.** One Room entity, DAO, repository methods, ViewModel state/actions, and Compose screens extend the existing application structure without a dependency or new architecture layer. Explicit migration preserves local matches and notes.

## Project Structure

```text
specs/018-favourite-heroes/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── quickstart.md
├── contracts/favourite-hero-ui.md
└── tasks.md

app/src/main/java/com/dtbuddy/app/
├── DTBuddyApplication.kt
├── data/{AppDatabase.kt, FavouriteHeroEntity.kt, FavouriteHeroDao.kt, LocalMatchRepository.kt}
└── heroes/{HeroSelectionScreen.kt, MatchHeroSelectionState.kt, MatchHeroSelectionViewModel.kt}

app/src/test/java/com/dtbuddy/app/{data/LocalMatchRepositoryTest.kt, heroes/MatchHeroSelectionViewModelTest.kt}
app/src/androidTest/java/com/dtbuddy/app/data/CompletedMatchDaoTest.kt
```

**Structure Decision**: Extend the existing data boundary, state holder, and single Compose navigation file. A separate favourite repository or feature module would be disproportionate for three local records.

## Complexity Tracking

No constitution violations or complexity exceptions.
