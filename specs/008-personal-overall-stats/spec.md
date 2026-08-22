# Feature Specification: Personal Overall Statistics

**Feature Branch**: `codex/008-personal-overall-stats`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: Add a local Profile summary showing games played, wins, losses, and win rate from completed unlinked matches.

## User Scenarios & Testing

### User Story 1 - View personal overall statistics (Priority: P1)

A solo player can open Profile and see a concise summary of their saved match results, so their local match log immediately answers how many games they have played and how often they have won.

**Why this priority**: The app can already log, save, and list local matches. This is the smallest statistics view required by Milestone 1 and makes those saved records useful without introducing more detailed statistics.

**Independent Test**: Save a known mix of won and lost matches, open Profile, and compare all four displayed values with the saved history.

**Acceptance Scenarios**:

1. **Given** the player has saved no matches, **When** they open Profile, **Then** the summary shows 0 games, 0 wins, 0 losses, and a 0% win rate.
2. **Given** the player has saved matches, **When** they open Profile, **Then** the summary shows the number of saved matches, player-perspective wins and losses, and win rate calculated as wins divided by games played.
3. **Given** the player saves another completed match and opens Profile, **When** the summary loads, **Then** it includes the newly saved match.
4. **Given** the player closes and reopens the app, **When** they open Profile, **Then** the summary is calculated from the same locally saved matches.

### Edge Cases

- A player with no saved matches sees a defined 0% rate rather than an empty or invalid value.
- A player with only wins sees 100%; a player with only losses sees 0%.
- Win rate is shown as a whole-number percentage, rounded to the nearest whole percent.

## Requirements

### Functional Requirements

- **FR-001**: Profile MUST show a clearly labelled personal overall summary containing games played, wins, losses, and win rate.
- **FR-002**: The summary MUST use every completed unlinked match stored on the device and must count results from the logging player's perspective.
- **FR-003**: Games played MUST equal the total number of stored completed matches; wins plus losses MUST equal games played.
- **FR-004**: Win rate MUST equal wins divided by games played, shown as a percentage rounded to the nearest whole percent; when no games exist, it MUST show 0%.
- **FR-005**: Profile MUST refresh the summary whenever it is opened, including after a newly saved match or app relaunch.
- **FR-006**: The feature MUST remain fully local and offline.
- **FR-007**: The feature MUST NOT add per-hero, matchup, turn-order, head-to-head, global, or community statistics; charts; favourites; notes; match editing or deletion; accounts; public profiles; linked opponents; or online features.

### Key Entities

- **Completed local match**: An existing saved, unlinked 1v1 result that supplies one game and either one player-perspective win or loss.
- **Personal overall summary**: A calculated local view containing games played, wins, losses, and rounded win rate; it is not separately stored.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual validation with a known mixed record, all four Profile values match the saved history exactly.
- **SC-002**: In automated checks, the summary correctly handles zero games, all wins, all losses, and a mixed record.
- **SC-003**: A player can reach their four-value summary from the launch screen in one selection.
- **SC-004**: After an app relaunch, 100% of locally saved matches remain represented in the Profile summary during manual validation.

## Assumptions

- Existing completed local matches are the only source of this summary; no data migration or new stored field is required.
- The existing Profile destination is the appropriate home for the local personal overview during the solo test.
- Rounding to the nearest whole percent keeps this first summary easy to scan; more detailed presentation can be decided with later statistics views.
