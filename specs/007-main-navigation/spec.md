# Feature Specification: Main Navigation

**Feature Branch**: `codex/navigation-shell`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: Add the approved permanent main navigation, with a Profile entry point to existing local match history.

## User Scenarios & Testing

### User Story 1 - Navigate the solo app (Priority: P1)

A solo player can move between the app's four planned main destinations, so the app feels like an ongoing match-tracking app rather than a one-way logging flow.

**Why this priority**: The project requires the public-launch navigation structure to be established during Milestone 1. Existing saved history is currently reachable only immediately after saving a match.

**Independent Test**: Launch the app and select each main destination, confirming that Log match remains usable and the other destinations clearly explain their current solo-test availability.

**Acceptance Scenarios**:

1. **Given** the app is newly opened, **When** the main screen appears, **Then** Log match is the selected destination and the existing guided log starts at its first step.
2. **Given** the player is on a main destination, **When** they choose another destination, **Then** that destination becomes selected and its screen appears.
3. **Given** the player opens Requests during the solo test, **When** the screen appears, **Then** it explains that linked-match requests are not available yet and shows no pending-request badge.
4. **Given** the player opens Global stats during the solo test, **When** the screen appears, **Then** it explains that community statistics are not available yet and does not show invented statistics.

---

### User Story 2 - Reopen local history (Priority: P1)

A solo player can open their saved local match history from Profile after a later app launch, so saved results remain useful beyond the confirmation screen.

**Why this priority**: Match history is already complete and durable, but its existing confirmation-screen entry point is deliberately temporary until main navigation and Profile are introduced.

**Independent Test**: Save a match, close and reopen the app, select Profile, and open Match history to confirm the saved result remains visible.

**Acceptance Scenarios**:

1. **Given** the player opens Profile, **When** the screen appears, **Then** it clearly identifies the solo device-local context and provides a Match history action.
2. **Given** saved matches exist, **When** the player selects Match history from Profile, **Then** the existing history screen shows the saved matches in its established order.
3. **Given** the player returns from Match history, **When** the back action is used, **Then** Profile is shown again.

### Edge Cases

- Switching away from an unfinished guided match must not save a partial match.
- Opening Profile with no saved matches must still allow Match history to show its existing empty state.
- Requests and Global stats must remain available as destinations without exposing accounts, linked matches, community data, or online behaviour.

## Requirements

### Functional Requirements

- **FR-001**: The app MUST provide the four confirmed main destinations: Log match, Requests, Profile, and Global stats.
- **FR-002**: Log match MUST be selected when the app opens and MUST retain the existing guided local match-log behaviour.
- **FR-003**: The selected main destination MUST be visually identifiable.
- **FR-004**: Requests MUST be a non-functional solo-test destination that clearly states linked-match requests are introduced in a later milestone and MUST show no badge or count.
- **FR-005**: Global stats MUST be a non-functional solo-test destination that clearly states community statistics are not available yet.
- **FR-006**: Profile MUST provide a clearly labelled action to open the existing local Match history.
- **FR-007**: Returning from Match history opened through Profile MUST return to Profile.
- **FR-008**: The feature MUST remain fully local and offline.
- **FR-009**: The feature MUST NOT add statistics calculations or data, profile details or editing, accounts, public identities, linked opponents, requests, badges, notes, favourites, match editing, match deletion, or online features.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual validation, a player can reach each of the four main destinations from the launch screen in one selection.
- **SC-002**: In manual validation after app relaunch, a player can reach Match history from Profile in two selections and see every previously saved local match.
- **SC-003**: In manual validation, all three unavailable-scope statements (Requests, Global stats, and Profile's local context) are visible without requiring sign-in or network access.
- **SC-004**: Existing automated checks for local match logging and history continue to pass.

## Assumptions

- The four primary destinations use a conventional persistent bottom navigation control, appropriate for the Android-first app.
- The existing guided match flow remains an internal flow within Log match; changing tabs does not promise to preserve an unfinished draft.
- Profile is intentionally minimal in this increment: it is a local navigation point, not the later public player-profile feature.
