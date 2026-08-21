# Implementation Plan: Local Match History

**Branch**: `codex/local-match-history` | **Date**: 2026-08-21 | **Spec**: [spec.md](spec.md)

## Summary

Show the existing completed local matches in a simple, read-only screen reached from the saved-match confirmation. The existing local repository gains an ordered read operation; the screen formats each saved match's date, heroes, and player-perspective result. No new stored data, production dependency, navigation shell, or online capability is needed.

## Technical Context

**Language/Version**: Kotlin with Java 17 toolchain

**Primary Dependencies**: Jetpack Compose, Navigation Compose, Room

**Test-only Dependencies**: AndroidX test runner and JUnit extension, added solely to run the real in-memory Room DAO ordering test on Android.

**Storage**: Existing Room/SQLite `completed_matches` table in private Android app storage

**Testing**: Existing JUnit local unit tests plus one Android instrumented Room DAO test. The instrumented test validates the production SQL query rather than a fake implementation.

**Target Platform**: Android 8.0 and later (API 26+)

**Project Type**: Android mobile app

**Performance Goals**: The small solo-test match list opens without a perceptible delay.

**Constraints**: Fully offline, no database schema change, preserve the approved save flow, and implement no feature beyond basic local history. The only dependency exception is the two test-only AndroidX artifacts required to execute the production Room query test; they add no runtime feature or user data access.

**Scale/Scope**: One repository read operation, focused tests, one read-only Compose destination, and one confirmation-screen action.

## Constitution Check

- Source documents were read and control scope: **pass**.
- The work is one small, approved Milestone 1 offline feature: **pass**.
- Existing Kotlin, Compose, Room, repository, and ViewModel structure is reused with no new production layer or dependency: **pass**. Two AndroidX test-only dependencies are justified by the required real Room-query coverage.
- The design neither collects new data nor introduces accounts, networking, or logs: **pass**.
- Tests and manual validation are planned: **pass**.

The post-design check remains **pass**: the design adds only a sorted local read, a read-only UI, and the test-only support needed to execute the real Room query.

## Project Structure

```text
app/src/main/java/com/dtbuddy/app/
├── data/
│   ├── CompletedMatchDao.kt             # ordered completed-match query
│   └── LocalMatchRepository.kt          # ordered history read boundary
└── heroes/
    ├── MatchHeroSelectionViewModel.kt   # history screen state coordination
    └── HeroSelectionScreen.kt           # history destination and UI

app/src/test/java/com/dtbuddy/app/
├── data/LocalMatchRepositoryTest.kt
└── heroes/MatchHeroSelectionViewModelTest.kt

specs/006-local-match-history/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/local-match-history-ui.md
├── quickstart.md
└── tasks.md
```

**Structure Decision**: Extend the existing small application structure. The repository remains the sole Room boundary; the existing ViewModel supplies screen state; Compose renders the new read-only destination.
