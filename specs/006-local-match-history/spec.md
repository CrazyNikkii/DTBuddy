# Feature Specification: Local Match History

**Feature Branch**: `codex/local-match-history`

**Created**: 2026-08-21

**Status**: Approved for implementation

**Input**: Add a basic local history of completed, unlinked matches.

## User Scenarios & Testing

### User Story 1 - Review Saved Matches (Priority: P1)

A solo player can open a history of the completed matches stored on their device, so they can check the results they have logged.

**Why this priority**: Match saving is already complete, but there is no way to view saved records. This completes the small, useful loop of logging a game and reviewing it later.

**Independent Test**: Save several matches, open history from the saved confirmation, and confirm every saved match is listed in the required order with its date, heroes, and result.

**Acceptance Scenarios**:

1. **Given** no completed match is stored, **When** the player opens match history, **Then** the app explains that there are no saved matches yet.
2. **Given** one or more completed matches are stored, **When** the player opens match history, **Then** every stored match shows its date played, both heroes, and whether the player won or lost.
3. **Given** stored matches have different played dates, **When** the player opens match history, **Then** later played dates appear before earlier played dates.
4. **Given** two stored matches have the same played date, **When** the player opens match history, **Then** the one saved more recently appears first.
5. **Given** the match-saved confirmation is visible, **When** the player chooses View match history, **Then** the history opens without starting another temporary log.

### Edge Cases

- A match saved immediately before opening history appears in the list.
- Matches retain the result that was recorded at save time; the list does not recalculate statistics.
- If the player has both won and lost matches, each row labels the result from the logging player's perspective.

## Requirements

### Functional Requirements

- **FR-001**: The app MUST provide a clearly labelled View match history action after a match is saved.
- **FR-002**: The history MUST include every completed unlinked match stored on the device.
- **FR-003**: Each history row MUST show the date played, the logging player's hero, the opponent's hero, and a clear Won or Lost result from the logging player's perspective.
- **FR-004**: The history MUST order matches by date played descending, then by most recently saved first; if saves have the same recorded time, the later stored match MUST appear first.
- **FR-005**: The history MUST show a clear empty state when no completed matches exist.
- **FR-006**: The feature MUST remain fully local and offline.
- **FR-007**: The feature MUST NOT add match details, editing, deletion, notes, favourites, profiles, accounts, statistics, opponent identities, linked matches, or online features.

### Key Entities

- **Completed local match**: An existing saved, unlinked 1v1 result. Its played date, both hero names, player-perspective result, and save time are used in history.

## Success Criteria

### Measurable Outcomes

- **SC-001**: A player can open their saved-match history from the post-save confirmation in one action.
- **SC-002**: In every manual validation run with at least three saved matches, 100% of the saved matches are shown with the required four pieces of information.
- **SC-003**: In every manual validation run with mixed played dates and a same-date tie, the visible ordering matches the defined deterministic ordering.
- **SC-004**: In every automated validation run, the repository returns saved matches in the defined order.

## Assumptions

- Existing saved matches are the only source for the initial list; no migration or new stored fields are needed.
- The match-saved confirmation is the appropriate temporary entry point until the approved profile and main navigation work is implemented.
- The existing local match-save flow and 45-hero catalogue are reused unchanged.
