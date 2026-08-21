# Research: Local Match History

## Decision: Sort stored matches in the database query

**Rationale**: The requirement defines a stable order by played date and then save time. Performing that ordering in the existing data query keeps every future consumer consistent and avoids duplicating sorting rules in the user interface.

**Alternatives considered**:

- Sort the list in the screen: rejected because it makes the UI responsible for data ordering and encourages later duplication.
- Add a new index or stored sort field: rejected because the current small local data set does not need either.

## Decision: Add test-only Android dependencies for real Room-query coverage

**Rationale**: A fake DAO cannot verify Room's generated implementation or the actual SQL `ORDER BY`. The AndroidX test runner and JUnit extension permit an in-memory Room database test on an Android runtime. They are test-scoped only and do not change the shipped app's user-facing capabilities, storage, networking, or runtime dependency graph.

**Alternatives considered**:

- Keep only a fake DAO test: rejected because it cannot prove the production query.
- Add a new production abstraction to make Room run in a local JVM test: rejected because it adds architecture purely for a small test and still does not exercise Android Room.

## Decision: Show history from the saved-match confirmation

**Rationale**: It creates a direct way to review the just-saved record without prematurely building the approved profile or four-destination navigation structure.

**Alternatives considered**:

- Add profile navigation now: rejected as a broader feature.
- Show the whole history automatically after every save: rejected because it removes the existing clear saved confirmation.

## Decision: Derive Won or Lost from the stored winner field

**Rationale**: The completed match already records whether the logging player or opponent won. A history-specific stored result would duplicate data and require a schema change.

**Alternatives considered**:

- Add a result column: rejected as redundant.
- Show only the raw winner: rejected because Won/Lost is clearer for the local owner.
