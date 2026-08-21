# Feature Specification: Select Match Outcome

**Feature Branch**: `codex/select-match-outcome-turn-order`

**Created**: 2026-08-21

**Status**: Complete

**Input**: Extend the completed hero-selection flow with the next approved guided choices only: choose the winner, then who went first. Show all four unfinished choices without saving a match.

## User Scenarios & Testing

### User Story 1 - Choose Match Outcome and Turn Order (Priority: P1)

A solo player who has chosen both heroes records whether they won or lost, then records which player went first. They can go back to correct any unfinished choice before the later date and review/save work exists.

**Why this priority**: Result and first player are the next required answers in the confirmed guided match-log order, immediately following the already completed hero choices.

**Independent Test**: Choose both heroes, choose the winner and first player, then use Back to change a choice and complete the flow again.

**Acceptance Scenarios**:

1. **Given** both heroes have been chosen, **When** the player selects whether they or their opponent won, **Then** the app retains that result and opens the first-player choice.
2. **Given** the first-player choice is open, **When** the player selects who went first, **Then** the app shows both heroes, the result, and the first-player choice in a clear temporary summary.
3. **Given** either new choice is open, **When** the player uses in-flow Back or Android system Back, **Then** the preceding unfinished choice is shown and earlier selections remain available to review or change.
4. **Given** a hero is changed after later choices were made, **When** the flow continues, **Then** choices that depend on the changed hero are cleared and must be selected again.

### Edge Cases

- Either player may be the winner and either player may go first, including when both selected heroes are the same canonical hero.
- If Android restores a result, first-player, or summary destination without the required unfinished choices, the app returns safely to the opening hero step.
- Leaving the app or restarting it discards the unfinished choices; saving a draft is outside this slice.

## Requirements

### Functional Requirements

- **FR-001**: After both heroes are chosen, the app MUST ask the player to choose either themselves or their opponent as the winner.
- **FR-002**: After a winner is chosen, the app MUST ask the player to choose either themselves or their opponent as the first player.
- **FR-003**: The result and first-player choices MUST identify the selected player plainly and retain the earlier hero choices for context.
- **FR-004**: After all four choices are made, the app MUST show a clear temporary summary of both heroes, winner, and first player.
- **FR-005**: In-flow Back and Android system Back MUST return through the guided flow without losing valid earlier choices.
- **FR-006**: Changing either hero MUST clear the result and first-player choices; changing the result MUST clear the first-player choice.
- **FR-007**: This feature MUST NOT collect a date, save a match, add Room storage, show history or statistics, create accounts, or require an internet connection.
- **FR-008**: If Android restores a later destination after unfinished choices have been discarded, the app MUST return safely to the opening hero step.

### Key Entities

- **Winner choice**: The temporary indication that either the logging player or opponent won the unfinished match.
- **First-player choice**: The temporary indication that either the logging player or opponent took the first turn.
- **Unfinished match choices**: The temporary pair of hero choices plus outcome and turn order; they are not a completed match record.

## Success Criteria

### Measurable Outcomes

- **SC-001**: Starting with two selected heroes, a player can reach the four-choice summary in exactly two additional taps.
- **SC-002**: In every manual validation run, returning with Back preserves all earlier valid choices and exposes the immediately preceding choice.
- **SC-003**: The summary identifies two heroes, one winner, and one first player before the player leaves the flow.

## Assumptions

- The existing 45-hero selection and temporary in-memory flow state are reused.
- The player-facing labels "You" and "Opponent" are sufficient because opponent identities are not part of Milestone 1's initial core build.
- The summary is an endpoint for this slice; date selection, review, and saving follow in later approved work.
