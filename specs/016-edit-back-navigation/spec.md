# Feature Specification: Edit Back Navigation

**Feature Branch**: `codex/private-match-notes`
**Created**: 2026-08-22
**Status**: Approved for implementation

## User Scenarios & Testing

### User Story 1 - Leave an edit safely (Priority: P1)

A player using Android's Back gesture during match editing is warned that unsaved changes will be lost, and can either continue editing or discard those changes and return to the screen they came from.

**Independent Test**: Start an edit from Match history, change a value, use Back, cancel once, then discard; confirm the original match remains unchanged and history is visible.

**Acceptance Scenarios**:

1. Given an unsaved edit, when the player uses Back, then a confirmation asks whether to discard unsaved changes.
2. Given the confirmation, when the player chooses Keep editing, then the edit stays open with its draft unchanged.
3. Given the confirmation, when the player chooses Discard changes, then the app returns to the preceding history screen and the stored match remains unchanged.

### User Story 2 - Use predictable Back navigation (Priority: P2)

A player can use Back to move one screen at a time through the app; the app exits only when no screen remains above the selected main destination.

**Acceptance Scenarios**:

1. Given a nested screen, when Back is used, then the immediately preceding screen is shown.
2. Given a main destination with no nested screen, when Back is used, then Android may exit the app.

### Edge Cases

- Repeated Back gestures while the discard confirmation is visible do not discard without the player choosing Discard changes.
- A saved edit does not show an unsaved-changes confirmation.

## Requirements

- **FR-001**: Android Back during an active unsaved edit MUST show a discard confirmation.
- **FR-002**: The confirmation MUST offer a clear Keep editing action and a destructive Discard changes action.
- **FR-003**: Discarding MUST make no write and return to the history screen from which editing began.
- **FR-004**: Existing Back behavior MUST return one nested screen at a time; the app exits only from a selected main destination with no remaining nested screen.
- **FR-005**: The feature MUST NOT add data fields, notes behaviour, accounts, online behaviour, or future-milestone features.

## Success Criteria

- **SC-001**: In manual testing, 100% of abandoned edits leave the stored match unchanged.
- **SC-002**: In manual testing, Back from editing no longer exits the app before the player chooses whether to discard changes.

## Assumptions

- Existing Match history is the appropriate return screen after discarding an edit, including edits opened through Profile.
