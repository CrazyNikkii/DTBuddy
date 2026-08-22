# Research: Edit Local Match

## Decisions

### Reuse the existing guided match-log flow for editing

**Decision**: Start the existing hero, outcome, first-player, date, and review sequence from the selected saved match's current choices.

**Rationale**: This keeps the editing experience consistent with logging, allows every approved match field to be corrected, and avoids a second collection of data-entry controls.

**Alternatives considered**:

- A separate compact edit form: rejected because it duplicates the guided flow and makes future validation inconsistent.
- Editing only the date or result: rejected because the confirmed product scope requires logged matches to be editable, including their recorded heroes and first player.

### Replace the selected record in place

**Decision**: Save an edit by targeting the selected record's existing identity and retaining its original creation time.

**Rationale**: It guarantees an edit cannot add a second match, preserves the approved history tie-breaker, and lets all existing statistics continue to derive from one local match list.

**Alternatives considered**:

- Delete and reinsert: rejected because it risks a temporary missing record and changes the saved-time ordering.
- Create a new corrected record: rejected because it leaves the incorrect result in history and double-counts statistics.

### Keep unfinished edits temporary

**Decision**: Do not write data until the player saves the final review.

**Rationale**: This matches the existing logging flow and makes back navigation or leaving the flow safe.

**Alternatives considered**:

- Save every individual selection immediately: rejected because it could leave partial or invalid saved matches.
