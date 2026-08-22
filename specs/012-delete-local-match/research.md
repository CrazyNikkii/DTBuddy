# Research: Delete Local Match

## Decision: Delete by the selected match's existing primary key

**Rationale**: The existing completed local match already has a unique stored identifier. Deleting by that identifier guarantees that identical-looking matches remain distinct and only the selected record is removed.

**Alternatives considered**:

- Delete by heroes, result, and date: rejected because several saved matches can share those values.
- Add a soft-delete/archive field: rejected because this small, disposable Milestone 1 data set has no approved undo or archive requirement and it would require a schema change.

## Decision: Use a blocking confirmation dialog from Match history

**Rationale**: Deletion is permanent, so the player needs an obvious final chance to cancel. The confirmation can identify the match with the existing date, heroes, and player-perspective result without adding a new detail screen.

**Alternatives considered**:

- Delete immediately: rejected because it gives no protection against an accidental tap.
- Add undo/snackbar recovery: deferred because it expands the approved scope and requires a temporary restore design.

## Decision: Reload history after successful deletion; retain derived-statistic calculations

**Rationale**: Match history needs immediate visible feedback. Existing statistics are already derived from the saved match records rather than persisted copies, so their normal loads automatically exclude a deleted match.

**Alternatives considered**:

- Store and update separate statistic totals: rejected by the existing statistics-integrity rule and unnecessary for the small local data set.
- Add observers or a new reactive layer: rejected as disproportionate to one small operation.
