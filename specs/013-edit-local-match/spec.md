# Feature Specification: Edit Local Match

**Feature Branch**: `codex/013-edit-local-match`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: User-approved feature: edit one saved local match from Match history, retaining its identity and updating every affected local statistic.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Correct a saved local match (Priority: P1)

A solo player can open one completed match from Match history, revise its heroes, result, first player, or played date, review the revised details, and save the correction on their device.

**Why this priority**: The existing app already records completed matches, displays history and all required personal statistics, and can remove a mistaken record. Editing is the remaining basic correction capability required for the solo test, without adding a new type of product data.

**Independent Test**: Save several known matches, edit one record, reopen Match history, and compare the history and all personal statistics with the revised set of results.

**Acceptance Scenarios**:

1. **Given** one or more completed local matches, **When** the player chooses Edit match for one history row, **Then** the app starts the existing guided choices with that match's current heroes, result, first player, and date available to review or revise.
2. **Given** the player changes one or more details and saves, **When** they return to Match history, **Then** exactly the selected record shows the revised details and remains a single record.
3. **Given** the player opens an edit flow, **When** they leave it without saving, **Then** the stored match and every statistic remain unchanged.
4. **Given** a saved edit changes the player hero, opponent hero, result, first player, or date, **When** the player reloads Profile views, **Then** the overall, hero, matchup, and turn-order statistics reflect the revised stored match.
5. **Given** two records otherwise look the same, **When** the player edits one of them, **Then** the other record remains unchanged.

### Edge Cases

- Editing the only saved match keeps one record in history and derives all personal statistics from its revised values.
- Editing a match so a player hero is no longer used removes that former hero from the personal Heroes list when it is reloaded.
- If the selected record no longer exists when saving, no other record is changed and the editing flow does not create a new record.
- The existing guided choice validation still prevents saving an incomplete edit.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The player MUST be able to begin editing an individual completed local match from Match history.
- **FR-002**: The edit flow MUST present the selected match's saved heroes, result, first player, and played date as the starting choices, and allow the player to change each of them.
- **FR-003**: The player MUST review the resulting set of choices before saving the edited match.
- **FR-004**: Saving an edit MUST replace the values of exactly the selected local match; it MUST NOT create an additional match or change another match.
- **FR-005**: Leaving an edit before saving MUST leave the stored match and derived statistics unchanged.
- **FR-006**: Match history MUST refresh immediately after a successful edit and retain its approved ordering by played date, then original save time, then record identity.
- **FR-007**: Overall, per-hero, matchup, and first-player/second-player statistics MUST derive from the edited local record when reloaded.
- **FR-008**: The feature MUST work fully offline and MUST NOT require accounts, a backend, or network access.
- **FR-009**: The feature MUST NOT add private notes, favourite heroes, match details beyond the edit/review flow, undo, accounts, linked opponents, requests, global or community statistics, or online features.

### Key Entities

- **Completed local match**: A saved, unlinked 1v1 result on the device, identified internally so the selected record can be replaced without creating another record.
- **Edit draft**: The temporary revised choices for one selected match. It changes stored data only after the player saves.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: In manual testing, 100% of saved edits change only the selected record and leave the total history-record count unchanged.
- **SC-002**: In manual testing, 100% of abandoned edits leave stored history and displayed statistics unchanged.
- **SC-003**: In a known multi-match test set, 100% of overall, hero, matchup, and turn-order values match the revised stored records after each edit.
- **SC-004**: A player can begin correcting a saved match from Match history in one action and save the correction through the existing guided flow.

## Assumptions

- The existing guided match-log screens are the appropriate way to revise each existing choice and show the final review.
- An edit keeps the selected record's original identity and original save-time tie-breaker; correcting a date may change only its date-based position in history.
- Closing or navigating away from an unfinished edit is an abandonment, not a data change.
