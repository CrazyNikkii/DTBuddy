# Feature Specification: Delete Local Match

**Feature Branch**: `codex/012-delete-local-match`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: User-approved feature: delete a saved local match from Match history with a clear confirmation step, and refresh the affected local history and statistics.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Delete an incorrect saved match (Priority: P1)

A solo player can remove an accidental or incorrect completed match from their device-local Match history after explicitly confirming the deletion.

**Why this priority**: Personal history and statistics are already complete read-only views. Removing incorrect records is the smallest confirmed correction capability and keeps every existing view trustworthy.

**Independent Test**: Save several known matches, delete one from Match history, and verify that it is absent after reopening the app and no longer affects every relevant local statistic.

**Acceptance Scenarios**:

1. **Given** the player has one or more saved matches, **When** they choose to delete one from Match history, **Then** the app shows which match is about to be deleted and asks for confirmation before changing data.
2. **Given** a deletion confirmation is shown, **When** the player cancels it, **Then** the match remains in Match history and all statistics stay unchanged.
3. **Given** a deletion confirmation is shown, **When** the player confirms it, **Then** only that saved match is removed from local history.
4. **Given** the player confirms deletion, **When** they return to Profile or reopen the app, **Then** overall, hero, matchup, and turn-order statistics exclude the deleted match.
5. **Given** the player has no saved matches, **When** they open Match history, **Then** no delete action is shown.

### Edge Cases

- Deleting the only saved match leaves Match history empty and resets all affected local totals to zero.
- Repeating a delete action cannot remove a different match after the original match has already been removed.
- Deleting a match with a hero that has no other saved matches removes that hero from the personal Heroes list.
- The player can still delete a match even if another saved match has the same heroes, result, date, or creation time.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The player MUST be able to start deletion for an individual completed local match from Match history.
- **FR-002**: Before deleting a match, the app MUST show a confirmation that identifies the selected match by its heroes, result, and date played.
- **FR-003**: The confirmation MUST offer a cancellation action that leaves all saved data unchanged.
- **FR-004**: Confirming deletion MUST remove exactly the selected completed local match from device-local storage.
- **FR-005**: The Match history screen MUST refresh to show the remaining saved matches immediately after a confirmed deletion.
- **FR-006**: Overall, per-hero, matchup, and first-player/second-player statistics MUST be calculated only from the remaining saved matches when reloaded.
- **FR-007**: The feature MUST work fully offline and MUST NOT require accounts, a backend, or network access.
- **FR-008**: The feature MUST NOT add match editing, notes, favourites, accounts, profiles beyond the existing local Profile, linked opponents, requests, global or community statistics, or online features.

### Key Entities

- **Completed local match**: A saved, unlinked 1v1 result on the device, identified internally so exactly that record can be deleted.
- **Deletion confirmation**: A temporary decision state for one selected saved match; cancelling it does not change data.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: In manual testing, 100% of confirmed deletions remove only the selected match from the saved Match history.
- **SC-002**: In manual testing, 100% of cancelled deletions leave history and statistics unchanged.
- **SC-003**: After each confirmed deletion in a known three-match test set, 100% of affected overall, hero, matchup, and turn-order values match the remaining saved matches.
- **SC-004**: A player can complete a confirmed deletion from Match history in no more than three actions after opening the history page.

## Assumptions

- Deletion is permanent for Milestone 1 because local test data is disposable and the approved local storage does not include an undo or archive feature.
- The existing Match history is the appropriate place to initiate deletion.
- A standard confirmation dialog that names the selected match provides sufficiently clear protection against accidental deletion for this small increment.
