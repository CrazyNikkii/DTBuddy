# Research: Select Match Date

## Decision: Use an Android platform date selector

**Rationale**: The platform control provides a familiar calendar-date interaction on Android 8.0 and later without adding a library or custom calendar interface. It fits the small offline slice.

**Alternatives considered**:

- Add a Compose date-picker dependency or experimental API: rejected because the existing project does not need another dependency for one date choice.
- Accept typed date text: rejected because it creates avoidable format and validation work for a beginner-facing guided flow.

## Decision: Store a date without a time of day

**Rationale**: The technical constraints require a match's date played to remain the calendar date selected by the player, without time-zone conversion. A date-only temporary value applies that rule before later persistence exists.

**Alternatives considered**:

- Store a timestamp: rejected because changing time zones could make the recorded date appear to change.
- Convert to UTC: rejected because it would not preserve the player's chosen calendar date.

## Decision: Reset later choices when an earlier answer changes

**Rationale**: The date follows the first-player choice in the guided flow. Changing a hero clears winner, first player, and date; changing winner clears first player and date; changing first player clears date. This prevents a stale later answer from being shown in a changed flow.

**Alternatives considered**:

- Keep the date after an earlier answer changes: rejected because the guided flow would then skip a required later step.
- Clear all hero choices for a date change: rejected because selecting a different date does not invalidate earlier answers.
