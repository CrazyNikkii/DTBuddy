# Feature Specification: Personal Hero Statistics List

**Feature Branch**: `codex/009-personal-hero-stats`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: Add a device-local Heroes page that lists every hero the solo player has used, with games played, wins, losses, and win rate.

## User Scenarios & Testing

### User Story 1 - View personal hero records (Priority: P1)

A solo player can open Heroes from Profile and see their record with each hero they have played, so they can understand which heroes they have used and how their results differ.

**Why this priority**: The app already saves the logging player's hero and shows a personal overall record. Per-hero records are the next required Milestone 1 statistics view and use the same local match data without adding detailed statistics.

**Independent Test**: Save a known mix of wins and losses with at least three player heroes, open Heroes from Profile, and compare every row with the saved match history.

**Acceptance Scenarios**:

1. **Given** no completed matches are saved, **When** the player opens Heroes from Profile, **Then** the app clearly explains that no heroes have been played yet.
2. **Given** completed matches exist for one player hero, **When** the player opens Heroes, **Then** the page shows that hero's games played, wins, losses, and whole-number win rate.
3. **Given** completed matches exist for multiple player heroes, **When** the player opens Heroes, **Then** the page shows one row for each player hero and each row uses only matches logged with that hero.
4. **Given** the player returns from Heroes, **When** the back action is used, **Then** Profile is shown again.
5. **Given** the player saves a later match using a new or existing hero, **When** they open Heroes, **Then** the list includes that match in the correct hero's record.

### Edge Cases

- A hero with only wins shows a 100% win rate; a hero with only losses shows 0%.
- A player hero that appears only once still has a complete row.
- A hero selected only as an opponent is not shown, because this page represents the logging player's played heroes.
- Hero rows are ordered alphabetically by hero name so the list is predictable.

## Requirements

### Functional Requirements

- **FR-001**: Profile MUST provide a clearly labelled action to open the device-local Heroes page.
- **FR-002**: Heroes MUST list every distinct hero used by the logging player in a completed local match, and MUST not list heroes used only by opponents.
- **FR-003**: Each listed hero MUST show games played, wins, losses, and win rate from matches logged with that hero.
- **FR-004**: A hero's games played MUST equal its logged completed-match count; its wins plus losses MUST equal its games played.
- **FR-005**: A hero's win rate MUST equal wins divided by games played, shown as a percentage rounded to the nearest whole percent.
- **FR-006**: Heroes MUST show an understandable empty state when the player has no saved matches.
- **FR-007**: Heroes MUST refresh from saved device-local matches whenever it is opened, including after saving another match or relaunching the app.
- **FR-008**: Hero rows MUST be ordered alphabetically by hero name.
- **FR-009**: The feature MUST remain fully local and offline.
- **FR-010**: The feature MUST NOT add hero details, matchup or first-player/second-player statistics, charts, favourites, notes, match editing or deletion, accounts, public profiles, linked opponents, requests, global or community statistics, or online features.

### Key Entities

- **Completed local match**: An existing saved 1v1 result. Its logging-player hero and player-perspective winner determine a hero record.
- **Personal hero record**: A derived view for one logging-player hero containing games played, wins, losses, and rounded win rate. It is not separately stored.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual validation with a known three-hero record, 100% of listed rows match the saved history's hero-specific games, wins, losses, and win rates.
- **SC-002**: Automated checks correctly cover an empty record, all wins, all losses, a mixed record, and the exclusion of opponent-only heroes.
- **SC-003**: A player can reach the Heroes list from the launch screen in two selections.
- **SC-004**: After an app relaunch, 100% of saved matches remain represented in their corresponding personal hero records during manual validation.

## Assumptions

- The existing completed local matches are the only data source; no database schema change or separately stored statistics are required.
- The existing Profile destination is the appropriate local entry point for Heroes in the solo test.
- Alphabetical order is a clear, stable first ordering; sorting by performance is deferred until a later approved statistics increment.
