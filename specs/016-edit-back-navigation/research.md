# Research: Edit Back Navigation

## Decision: Handle Back explicitly in the edit overview

**Rationale**: The edit overview can be the first route in its navigation stack, so default Android Back exits the activity. An explicit handler can ask before discarding the in-memory edit draft.

## Decision: Discard state without writing

**Rationale**: The repository must not be called when the player abandons an edit; the existing stored match therefore remains intact.
