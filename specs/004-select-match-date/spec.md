# Feature Specification: Select Match Date

**Feature Branch**: `codex/select-match-date`

**Created**: 2026-08-21

**Status**: Complete

**Input**: Add the approved guided date-played choice after match outcome and first-player selection. It defaults to today, is editable, appears in the temporary summary, and does not save a match.

## User Scenarios & Testing

### User Story 1 - Choose Date Played (Priority: P1)

A solo player who has chosen both heroes, the winner, and who went first records the calendar date on which the completed match was played. Today is already selected, but they can choose another date before seeing the temporary summary.

**Why this priority**: The date is the next required answer in the confirmed guided match-log order. It is needed before a later review-and-save slice can create a completed match.

**Independent Test**: Choose both heroes, winner, and first player; confirm the date step starts on today, select another date, and confirm the temporary summary shows that date.

**Acceptance Scenarios**:

1. **Given** the player has selected both heroes, winner, and first player, **When** the first-player choice is completed, **Then** the app opens a date-played step with today's calendar date selected.
2. **Given** the date-played step is open, **When** the player chooses another calendar date, **Then** the date step and temporary summary show that selected date.
3. **Given** the temporary summary is visible, **When** the player uses in-flow Back or Android system Back, **Then** the date step is shown with its selected date still available to review or change.
4. **Given** the player changes a hero, winner, or first-player answer after a date was selected, **When** the flow continues, **Then** later dependent choices, including the date, are cleared and selected again.

### Edge Cases

- The player can keep today's prefilled date or choose any calendar date offered by the device date selector.
- A selected date represents that calendar date only; it does not change when the device time zone changes.
- If Android restores the date or summary destination after unfinished choices have been discarded, the app returns safely to the opening hero step.
- Leaving the app or restarting it discards the unfinished date and all other unfinished choices; saving a draft is outside this slice.

## Requirements

### Functional Requirements

- **FR-001**: After both heroes, the winner, and first player are chosen, the app MUST ask for the date played.
- **FR-002**: The date-played step MUST prefill today's local calendar date and allow the player to choose a different calendar date.
- **FR-003**: After a date is selected, the app MUST show a clear temporary summary of both heroes, winner, first player, and date played.
- **FR-004**: In-flow Back and Android system Back MUST return from the summary to the date step without losing a selected date.
- **FR-005**: Changing either hero, winner, or first-player choice MUST clear any later dependent choices, including the date played when it is no longer valid in the guided flow.
- **FR-006**: This feature MUST NOT save a match, add Room storage, create a draft, show history or statistics, add notes or favourites, create accounts, or require an internet connection.
- **FR-007**: If Android restores a later destination without the required unfinished choices, the app MUST return safely to the opening hero step.

### Key Entities

- **Date played**: The selected calendar date of an unfinished match. It has no time of day and remains temporary until a later save feature exists.
- **Unfinished match choices**: The temporary player hero, opponent hero, winner, first player, and date played; they are not a completed match record.

## Success Criteria

### Measurable Outcomes

- **SC-001**: After choosing the first player, a player reaches a summary containing all five unfinished choices in at most two further actions when keeping today's date.
- **SC-002**: In every manual validation run, returning with Back from the summary preserves the selected date and exposes the date step.
- **SC-003**: The temporary summary identifies two heroes, one winner, one first player, and one calendar date before the player leaves the flow.

## Assumptions

- The existing 45-hero selection and temporary in-memory flow state are reused.
- The device's local date is the appropriate default because this solo, offline slice has no account or shared time-zone data.
- Date selection and the temporary summary do not create a saved draft or completed match; review and save remain later approved work.
