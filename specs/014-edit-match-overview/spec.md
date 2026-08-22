# Feature Specification: Edit Match Overview

**Feature Branch**: `codex/013-edit-local-match`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: User-approved refinement: editing a local match opens a clickable overview, so the player changes only the incorrect field rather than repeating the full logging sequence.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Correct one detail from an overview (Priority: P1)

A solo player opens a saved match's edit overview, taps only the detail they need to correct, makes the replacement in the existing selection screen, returns to the overview, and saves.

**Why this priority**: Manual testing showed that requiring every guided choice during editing makes a small correction unnecessarily error-prone.

**Independent Test**: Edit only who went first in a saved match, then verify all other saved values are unchanged and statistics reflect only that correction.

**Acceptance Scenarios**:

1. **Given** a saved local match, **When** the player chooses Edit match, **Then** they see an overview containing clickable player hero, opponent hero, winner, first player, and played date values.
2. **Given** the overview, **When** the player taps one value and makes a selection, **Then** the app returns to the overview with only that value changed.
3. **Given** the overview, **When** the player saves without changing a value, **Then** the stored record remains unchanged.
4. **Given** a revised overview, **When** the player saves changes, **Then** exactly the selected local match is updated and statistics reload from it.

### Edge Cases

- Leaving the overview or a choice screen without saving changes no stored match.
- Changing either hero does not force the player to re-enter the remaining edit values.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: Editing a saved local match MUST begin on a clickable overview of all five saved match values.
- **FR-002**: Each overview value MUST open only its corresponding existing choice screen.
- **FR-003**: Choosing a replacement during editing MUST return to the overview and preserve all other current edit values.
- **FR-004**: The overview MUST provide Save changes and must not write until that action.
- **FR-005**: Normal new-match logging MUST retain its existing guided flow.
- **FR-006**: The feature MUST remain local and offline and MUST NOT add notes, undo, accounts, linked opponents, requests, global statistics, or network behaviour.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: A player can correct one saved value by opening Edit match, selecting that value, and saving without revisiting unrelated questions.
- **SC-002**: In manual testing, 100% of one-field edits retain the other four stored values.
- **SC-003**: In manual testing, 100% of abandoned edits leave the saved match unchanged.

## Assumptions

- The current hero, participant, and date screens remain the right controls for making an individual replacement.
- The edit overview replaces the current full-sequence edit route only; it does not change new-match logging.
