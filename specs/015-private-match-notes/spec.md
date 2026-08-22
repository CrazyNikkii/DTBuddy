# Feature Specification: Private Match Notes

**Feature Branch**: `codex/private-match-notes`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: User-approved increment: add an optional private note to a locally saved match.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Save and revisit a private match note (Priority: P1)

A solo player can optionally write a short note while logging a completed match, then see that note with the saved match in their local history.

**Why this priority**: The local logging, history, and statistics loop is complete. Notes are the next explicitly confirmed Milestone 1 increment and add context without expanding into social or online features.

**Independent Test**: Save one match with a note and one without, open Match history, and confirm each match retains the correct private-note state after an app relaunch.

**Acceptance Scenarios**:

1. **Given** a player has completed the normal match choices, **When** they enter a note of up to 500 characters and save, **Then** the saved match retains that note locally.
2. **Given** a saved match has a note, **When** the player opens Match history, **Then** its row displays the note alongside that match's existing private details.
3. **Given** a player leaves the note blank, **When** they save a match, **Then** the match saves normally and history does not display an empty note area.
4. **Given** existing saved matches were created before notes, **When** the player updates to this version and opens history, **Then** those matches remain available and have no note.

### User Story 2 - Correct or remove a private match note (Priority: P2)

A solo player can add, change, or clear a private note while editing one saved match, without changing its other saved values until they save the edit.

**Why this priority**: A note is personal context and needs the same correction path as the existing local match fields.

**Independent Test**: Edit a saved note, save it, then start another edit and abandon a removal; verify only the saved edit changes the stored note.

**Acceptance Scenarios**:

1. **Given** a saved local match, **When** the player chooses Edit match, **Then** the edit overview provides a note field with the saved note, if any.
2. **Given** the player changes or clears the note, **When** they save changes, **Then** only the selected match's note is updated and its other stored values remain unchanged.
3. **Given** the player changes a note during editing, **When** they leave without saving, **Then** the stored note remains unchanged.

### Edge Cases

- A note at exactly 500 characters can be saved; additional characters cannot be entered.
- Whitespace-only input is treated as no note.
- Notes remain device-local and are never included in statistics.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The match review before saving MUST offer an optional note field limited to 500 characters.
- **FR-002**: A non-empty note MUST be stored only with its selected completed local match; blank or whitespace-only input MUST be stored as no note.
- **FR-003**: Match history MUST display a saved note with its own match and MUST omit the note area when a match has no note.
- **FR-004**: Edit match MUST show the current note and allow the player to add, replace, or clear it before Save changes.
- **FR-005**: Creating or changing a note MUST NOT alter the match's heroes, result, first-player value, played date, creation time, history order, or calculated statistics.
- **FR-006**: Existing saved local matches MUST remain available after the note field is added and MUST be treated as having no note.
- **FR-007**: The feature MUST remain fully local and offline. Notes MUST NOT be sent over a network, logged, or exposed publicly.
- **FR-008**: The feature MUST NOT add favourites, accounts, opponent identities, free-text opponent names, linked matches, requests, community or global statistics, cloud backup or sync, or public profiles.

### Key Entities

- **Private match note**: Optional short personal text belonging to exactly one completed local match. It is stored only on the player's device and has no role in statistics.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: In manual validation, a note of 500 characters and a blank note both save successfully with the expected history display.
- **SC-002**: In automated checks, adding, editing, clearing, and abandoning a note edit preserve the correct stored match data in 100% of tested cases.
- **SC-003**: In manual validation after an app relaunch, 100% of notes and pre-existing no-note matches remain attached to their original local matches.
- **SC-004**: No note changes the displayed games played, wins, losses, win rate, hero, matchup, or turn-order statistics in validation with known match data.

## Assumptions

- A 500-character maximum keeps notes short and readable on a mobile history row.
- The existing review screen and edit overview are the appropriate places to enter or change a note; Match history is the appropriate place to revisit it.
- Notes are plain text and do not require formatting, attachments, search, export, or separate detail pages.
