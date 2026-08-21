# Feature Specification: Save Local Match

**Feature Branch**: `codex/save-local-match`

**Created**: 2026-08-21

**Status**: Implemented — manual Android validation pending

**Input**: Save the approved completed, unlinked 1v1 match locally after the existing guided choices. Keep it offline and intentionally exclude history, statistics, editing, notes, favourites, accounts, and networking.

## User Scenarios & Testing

### User Story 1 - Save a Completed Match (Priority: P1)

A solo player who has selected both heroes, winner, first player, and date played saves that completed match on their device.

**Why this priority**: Saving turns the existing temporary guided flow into a useful match log and supplies the local records required by later history and statistics work.

**Independent Test**: Complete the current guided flow, save the match, restart the app, and confirm the stored match remains available through the feature's limited saved-match confirmation.

**Acceptance Scenarios**:

1. **Given** the temporary summary contains both heroes, winner, first player, and date played, **When** the player selects Save match, **Then** the app stores one completed unlinked match on this device and shows a clear success confirmation.
2. **Given** a match was saved, **When** the app is closed and opened again, **Then** that saved match remains stored on the device.
3. **Given** the success confirmation is visible, **When** the player chooses to log another match, **Then** the app starts at the opening hero step with no previous temporary choices.
4. **Given** the temporary summary is missing any required choice, **When** it is opened after Android restores app state, **Then** the app returns safely to the opening hero step without saving a partial match.

### Edge Cases

- Selecting Save match more than once while the first save is still being processed must create only one stored match.
- Closing the app before Save match is selected must not store an unfinished match.
- A saved match has no opponent identity, link, note, account, or network state.

## Requirements

### Functional Requirements

- **FR-001**: The temporary five-choice summary MUST provide a clearly labelled Save match action.
- **FR-002**: Saving MUST store both selected heroes, winner, first player, and date played as one completed unlinked 1v1 match on the device.
- **FR-003**: The app MUST retain saved completed matches after the app is closed and reopened.
- **FR-004**: After a successful save, the app MUST clearly confirm that the match was saved locally and let the player begin a new match log.
- **FR-005**: Beginning a new match log after a save MUST clear all temporary choices from the previous match.
- **FR-006**: The app MUST NOT save an incomplete match.
- **FR-007**: This feature MUST remain fully local and offline. It MUST NOT add match history, statistics, editing, deletion, private notes, favourite heroes, accounts, opponent identities, linking, or online features.

### Key Entities

- **Completed local match**: One saved unlinked 1v1 result containing the two hero choices, winner, first player, and calendar date played. It is associated only with the device-local owner and has no opponent identity.
- **Temporary match choices**: The in-progress selections shown before saving. They are discarded when a new log begins or the app is stopped before a save.

## Success Criteria

### Measurable Outcomes

- **SC-001**: A player who has completed the five existing choices can save a match in one action.
- **SC-002**: In every manual validation run, a saved match remains available after one app restart.
- **SC-003**: In every automated validation run, exactly one completed match is stored for one completed save action.

## Assumptions

- The existing guided flow and its 45-hero catalogue are reused without changing the questions or their order.
- A concise confirmation is sufficient until a dedicated match-history screen is approved.
- The device-local owner profile is implicit at this stage; the app does not yet expose profile management.
