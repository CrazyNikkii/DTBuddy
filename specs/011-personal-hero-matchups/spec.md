# Feature Specification: Personal Hero Matchup Table

**Feature Branch**: `codex/011-personal-hero-matchups`

**Created**: 2026-08-22

**Status**: Implemented

**Input**: Add a device-local matchup table to a selected personal hero's detail page. It lists each opponent hero the player has faced with the selected hero, with games played, wins, losses, and win rate.

## User Scenarios & Testing

### User Story 1 - View a hero's matchup record (Priority: P1)

A solo player can open a hero from Profile's Heroes list and see their record with that hero against each opposing hero they have faced.

**Why this priority**: The app already provides overall personal statistics, per-hero statistics, and first-player/second-player splits. This completes the remaining confirmed personal-statistics dimension for Milestone 1 using the saved matches already on the device.

**Independent Test**: Save known wins and losses with one player hero against two different opponent heroes, open that player's hero detail, and compare every matchup row with the saved match history.

**Acceptance Scenarios**:

1. **Given** the player has saved matches using Barbarian against Moon Elf, **When** they open Barbarian's detail, **Then** the matchup table shows Moon Elf with the correct games played, wins, losses, and whole-number win rate.
2. **Given** the player has saved Barbarian matches against Moon Elf and Pyromancer, **When** they open Barbarian's detail, **Then** the table shows one separately calculated row for each opponent hero.
3. **Given** the player has saved matches against the same opponent hero with mixed results, **When** they open the selected hero's detail, **Then** that opponent's row combines every matching result exactly once.
4. **Given** the selected hero has no saved matches, **When** its detail is considered, **Then** no matchup rows exist; ordinary hero-detail navigation still remains available only from a played hero.
5. **Given** the player saves a later match with the selected hero, **When** they reopen that hero detail, **Then** its matchup table includes the later match.

### Edge Cases

- A matchup with only wins shows 100%; one with only losses shows 0%.
- A matchup row with one win in three games shows 33% after whole-number rounding.
- A hero used only by the opponent appears only as the opponent in a relevant matchup row; it does not become a personal hero row by itself.
- The selected hero's overall record continues to count each saved match once; matchup rows are an alternative breakdown and must never be added together with an overall total.

## Requirements

### Functional Requirements

- **FR-001**: A selected personal hero detail MUST show a clearly labelled matchup table.
- **FR-002**: The table MUST contain one row for every distinct opponent hero faced in completed local matches where the logging player used the selected hero.
- **FR-003**: Each matchup row MUST show the opponent hero name, games played, wins, losses, and win rate from the logging player's perspective.
- **FR-004**: Each matchup's wins plus losses MUST equal its games played.
- **FR-005**: Each matchup win rate MUST be wins divided by games played, rounded to the nearest whole percentage.
- **FR-006**: Each completed selected-hero match MUST contribute exactly once to its opponent-hero matchup row.
- **FR-007**: Matchup rows MUST refresh from saved device-local matches whenever the selected hero detail is opened, including after saving another match or relaunching the app.
- **FR-008**: Matchup rows MUST be ordered alphabetically by opponent hero name.
- **FR-009**: The feature MUST remain fully local and offline.
- **FR-010**: The feature MUST NOT add global/community statistics, charts, sorting controls, favourites, notes, match editing or deletion, accounts, public profiles, linked opponents, requests, or online features.

### Key Entities

- **Completed local match**: An existing saved, unlinked 1v1 result. Its logging-player hero, opponent hero, and player-perspective winner determine matchup records.
- **Personal hero matchup record**: A derived record for one selected player hero against one opponent hero. It contains games played, wins, losses, and a rounded win rate and is not stored separately.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual validation with known results against at least two opponent heroes, 100% of matchup-row values match saved match history.
- **SC-002**: Automated checks cover no matchup rows, all wins, all losses, mixed records, rounding, alphabetic ordering, and the rule that each selected-hero match occurs in one matchup row.
- **SC-003**: A player can reach a selected hero's matchup table from the launch screen in three selections.
- **SC-004**: After an app relaunch, 100% of a selected hero's saved matchup results remain represented during manual validation.

## Assumptions

- Existing completed local matches are the only data source; no database schema change or separately stored statistics are required.
- The existing selected hero detail is the appropriate place for the matchup table alongside the existing overall and turn-order records.
- Alphabetical opponent-hero ordering is a clear and stable first order; performance sorting is deferred.
