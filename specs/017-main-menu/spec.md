# Feature Specification: Main Menu

**Feature Branch**: `codex/main-menu`  
**Created**: 2026-08-22  
**Status**: Approved for implementation

## User Scenarios & Testing

### User Story 1 - Start from the main menu (Priority: P1)

A solo player opens DTBuddy on a main menu with a placeholder DTBuddy mark and a prominent Log a match action. Selecting it starts the existing guided flow.

**Acceptance Scenarios**:

1. Given the app opens, when the player sees the Log match destination, then they see the main menu instead of hero selection.
2. Given the main menu, when the player chooses Log a match, then the existing player-hero question opens with an empty new-match draft.
3. Given the first match-log question, when the player uses Back, then the app asks before discarding the unfinished log and returns to the main menu on confirmation.
4. Given a later guided question, when the player uses Back, then the previous guided question appears without a discard confirmation.
5. Given a match has been saved, when the player uses Back from its saved confirmation, then the main menu appears rather than any logging question.
6. Given the player is at Requests, Profile, or Global stats with no nested screen open, when they use Back, then the main menu appears instead of the app closing.
7. Given the player is on the main menu, when they use Back once, then a brief message explains that Back again exits; when they use Back again promptly, then the app closes.

## Requirements

- **FR-001**: Log match MUST open on a main menu that uses placeholder text for the DTBuddy logo and has one prominent Log a match action.
- **FR-002**: The action MUST begin the existing guided new-match flow without changing its choices, saving, notes, history, or statistics.
- **FR-003**: Back from the first guided question MUST ask before discarding the unfinished new-match draft; keep editing retains it and discard returns to the main menu.
- **FR-004**: Back from later guided questions MUST return one question at a time.
- **FR-005**: The main menu MUST be the Back-navigation root for Log match.
- **FR-006**: Back from a saved-match confirmation MUST return directly to the main menu and MUST NOT reopen any match-log question.
- **FR-007**: Back from a non-main-menu primary destination MUST return to the main menu before the app can close.
- **FR-008**: The first Back gesture from the main menu MUST show a brief Press Back again to exit message; a second gesture within two seconds MUST close the app.
- **FR-009**: The feature MUST NOT add a final logo asset, accounts, online behaviour, favourites, or new match data.

## Success Criteria

- **SC-001**: In manual testing, app launch reaches the main menu and Log a match reaches hero selection in one tap.
- **SC-002**: In manual testing, 100% of discarded new logs leave no saved match.

## Assumptions

- The mockup's wording and hierarchy guide this increment; the product uses Compose text and existing original UI elements rather than copying the image's artwork.
