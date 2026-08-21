# Research: Save Local Match

## Decision: Use AndroidX Room 2.8.4 with Kotlin Symbol Processing

**Rationale**: The technical constraints explicitly require Room backed by SQLite for Milestone 1. Android's Room release notes list 2.8.4 as the latest stable `androidx.room` release. Kotlin Symbol Processing is compatible with the project's Android Gradle Plugin built-in Kotlin support and keeps the project free of a dependency-injection framework.

**Alternatives considered**:

- Raw SQLite: rejected because Room is the approved storage boundary.
- Room 3: rejected because it is a separate Kotlin Multiplatform package and this Android-only app has no need to make that migration in this small slice.
- In-memory storage: rejected because the approved purpose is to retain saved matches after restart.

## Decision: Store participant values as stable enum names and the played date as ISO calendar text

**Rationale**: Both are simple, local, and preserve exactly the choices already made by the guided flow. The played date remains a date without time-zone conversion as required by the technical constraints.

**Alternatives considered**:

- Saving formatted labels: rejected because display formatting can change and would not be a stable value.
- Saving a timestamp for the date played: rejected because it could change the selected calendar date across time zones.

## Decision: Confirm the save rather than add history now

**Rationale**: A confirmation makes the persisted result visible and lets the player immediately log another match, while staying inside the approved scope. A history screen is a separate forthcoming slice.

**Alternatives considered**:

- Add a first history list: rejected as out of scope for this deliberately small feature.
- Return silently to the hero selector: rejected because the player needs clear confirmation that the save succeeded.
