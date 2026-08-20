# Feature Specification: Select Match Heroes

**Feature Branch**: `codex/select-match-heroes`

**Created**: 2026-08-20

**Status**: Complete

**Input**: Turn the existing hero browser into the first two steps of the approved guided match-log flow: choose the logging player's hero, then the opponent's hero. Keep the work limited to these choices.

## User Scenarios & Testing

### User Story 1 - Choose Both Match Heroes (Priority: P1)

A solo player begins a new match log, chooses their own hero from the approved roster, then chooses the opponent's hero. They can go back to correct the first choice.

**Why this priority**: Hero choices are the first two required answers in the confirmed guided match-log flow. The current app can browse the roster but cannot yet make a choice.

**Independent Test**: Start the app, choose a hero through browsing or search, choose an opponent hero, then use Back and choose a different logging-player hero.

**Acceptance Scenarios**:

1. **Given** the app has opened, **When** the player selects a hero, **Then** the app records it as the logging player's hero and opens the opponent-hero step.
2. **Given** the opponent-hero step is open, **When** the player selects a hero, **Then** the app clearly confirms both selected heroes.
3. **Given** the opponent-hero step is open, **When** the player goes back, **Then** the app returns to the logging-player step with the earlier choice still visible and changeable.
4. **Given** either hero step is open, **When** the player searches, **Then** search covers the complete frozen roster and does not change the other hero choice.

### Edge Cases

- A search with no matching hero shows the existing no-results message and cannot create a selection.
- A player may choose the same canonical hero for both players; the current requirements do not forbid mirror matches.
- Leaving the app or restarting it discards the unfinished choices; if Android restores a later flow destination without those choices, the app returns safely to the opening hero step. Saving drafts is outside this slice.

## Requirements

### Functional Requirements

- **FR-001**: The app MUST present the existing 45-hero browse and search experience as the logging-player hero step when it opens.
- **FR-002**: Selecting a hero at the logging-player step MUST proceed to an opponent-hero step and retain the selected logging-player hero.
- **FR-003**: Selecting a hero at the opponent-hero step MUST show an explicit confirmation of both selected heroes.
- **FR-004**: The opponent-hero step MUST provide an in-flow Back action and support Android system Back; both return to the logging-player step without losing its selection.
- **FR-005**: Each step MUST allow the player to browse the four approved groups and search the complete frozen roster.
- **FR-006**: This feature MUST NOT save a match, collect result/turn order/date, create accounts, or require an internet connection.
- **FR-007**: If Android restores the opponent or confirmation destination after unfinished choices have been discarded, the app MUST return safely to the logging-player hero step.

### Key Entities

- **Hero choice**: One canonical hero selected for either the logging player or the opponent in an unfinished match log.
- **Unfinished match choices**: The temporary pair of hero choices shown during this flow; it is not a completed match record.

## Success Criteria

### Measurable Outcomes

- **SC-001**: A player can choose both heroes from the opening screen in no more than two hero taps.
- **SC-002**: Each hero step exposes all 45 approved heroes through browse or search.
- **SC-003**: Returning to the first step preserves the chosen logging-player hero in 100% of the manual validation runs.

## Assumptions

- The existing canonical roster and search behavior remain the source for both choices.
- The confirmation after choosing an opponent is an endpoint for this small slice, not a saved match or the later result step.
- Android system Back closes the app from the opening step; the in-flow Back action is required only from the opponent step.
