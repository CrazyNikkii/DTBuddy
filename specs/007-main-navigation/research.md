# Research: Main Navigation

## Decision: Use persistent bottom navigation for the four main destinations

**Rationale**: The confirmed information architecture has four peer destinations. A persistent bottom navigation control makes all of them discoverable and gives the selected destination a clear visual state on Android.

**Alternatives considered**:

- A menu or drawer: rejected because it hides the required primary destinations and makes the app less immediately understandable.
- Keep history only after saving: rejected because the existing history specification explicitly identifies this as a temporary entry point.

## Decision: Keep Requests and Global stats as explicit solo-test placeholders

**Rationale**: The product requires the navigation structure now, while the related multi-user and community capabilities belong to a later milestone. Clear, non-functional messages establish the structure without misrepresenting unavailable data.

**Alternatives considered**:

- Omit the destinations until their capabilities exist: rejected because it conflicts with the confirmed Milestone 1 navigation requirement.
- Add preview statistics or requests: rejected because that exceeds the approved scope and would create unsupported product behaviour.

## Decision: Keep Profile minimal and local

**Rationale**: Profile only needs to be the persistent home for the existing local Match history in this increment. Creating a profile overview, hero statistics, favourites, or a public identity would all be later work.

**Alternatives considered**:

- Build the planned public profile now: rejected because it requires functionality beyond the approved small piece.
- Add a standalone History tab: rejected because it would diverge from the confirmed four destinations.
