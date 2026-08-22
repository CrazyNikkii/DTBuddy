# Feature Specification: Personal Hero Turn-Order Detail

**Feature Branch**: `codex/010-hero-turn-order-stats`

**Created**: 2026-08-22

**Status**: Implemented

**Input**: Add a device-local detail page for a selected personal hero. It shows the hero's overall record and separate first-player and second-player records, each with games played, wins, losses, and win rate.

## User Scenarios & Testing

### User Story 1 - View a hero's turn-order record (Priority: P1)

A solo player can open a hero from Profile's Heroes list and see how their results with that hero differ when they went first and when the opponent went first.

**Why this priority**: The app already stores who went first, calculates the player's overall and per-hero records, and lists played heroes. This small detail view delivers the next required personal-statistics dimension without adding a new type of saved data.

**Independent Test**: Save known wins and losses for one player hero under both turn orders, open that hero from Profile's Heroes page, and compare all three records with saved match history.

**Acceptance Scenarios**:

1. **Given** the player has a row for a hero in Heroes, **When** they select that hero, **Then** the app opens a page headed by that hero's name and shows its overall games, wins, losses, and whole-number win rate.
2. **Given** the player has logged matches with the selected hero while both going first and going second, **When** they open its detail, **Then** the page separately shows accurate first-player and second-player games, wins, losses, and win rates.
3. **Given** the selected hero has logged matches in only one turn order, **When** they open its detail, **Then** the unused turn-order section clearly shows zero games, wins, losses, and win rate.
4. **Given** the player is viewing a hero detail, **When** they use the page Back action or Android system Back, **Then** Heroes is shown again.
5. **Given** the player saves a later match with a hero, **When** they reopen that hero from Heroes, **Then** its overall and turn-order records include the saved match.

### Edge Cases

- A selected hero with only wins shows 100%; one with only losses shows 0%.
- A one-game turn-order record remains complete and has the correct 0% or 100% win rate.
- A hero used only by an opponent remains absent from Heroes and cannot be opened.
- The overall hero record counts every match for that hero exactly once; first-player and second-player records partition the same matches and are not added to any wider statistic.

## Requirements

### Functional Requirements

- **FR-001**: Each row in Profile's device-local Heroes list MUST provide an understandable action to open that hero's detail.
- **FR-002**: A hero detail MUST show the selected hero's overall games played, wins, losses, and win rate from completed local matches where the logging player used that hero.
- **FR-003**: A hero detail MUST separately show records for matches where the logging player went first and where the opponent went first.
- **FR-004**: Each displayed record MUST show games played, wins, losses, and win rate rounded to the nearest whole percentage.
- **FR-005**: For every displayed record, wins plus losses MUST equal games played.
- **FR-006**: The selected hero's overall games played MUST equal the sum of its first-player and second-player games played; each match MUST contribute to exactly one of those two sections.
- **FR-007**: Hero details MUST refresh from saved device-local matches whenever they are opened, including after saving another match or relaunching the app.
- **FR-008**: Back from a hero detail MUST return to the Heroes list.
- **FR-009**: The feature MUST remain fully local and offline.
- **FR-010**: The feature MUST NOT add matchup results, charts, sorting controls, favourites, notes, match editing or deletion, accounts, public profiles, linked opponents, requests, global or community statistics, or online features.

### Key Entities

- **Completed local match**: An existing saved 1v1 result. Its logging-player hero, player-perspective winner, and first-player choice determine the selected hero's records.
- **Personal hero turn-order detail**: A derived view for one logging-player hero containing one overall record and two mutually exclusive turn-order records. It is not separately stored.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual validation with known first-player and second-player results, 100% of the detail's overall and turn-order values match saved history.
- **SC-002**: Automated checks cover empty turn-order sections, all wins, all losses, mixed records, and the rule that each match appears in exactly one turn-order section.
- **SC-003**: A player can reach a selected hero's detail from the launch screen in three selections.
- **SC-004**: After an app relaunch, 100% of the selected hero's saved matches remain represented in the same overall and turn-order records during manual validation.

## Assumptions

- Existing completed local matches are the sole data source; no database schema change or separately stored statistics are required.
- A tap anywhere on the existing hero row is a clear action for opening its detail.
- The detail shows the existing hero's overall record as context alongside its first-player and second-player sections; matchup statistics remain a separate future increment.
