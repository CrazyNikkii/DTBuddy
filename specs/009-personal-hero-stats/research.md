# Research: Personal Hero Statistics List

## Decision 1: Derive one record per logging-player hero from the existing match query

**Decision**: Read the saved completed matches through the existing local repository, group them by `playerHeroName`, and calculate each record in memory.

**Rationale**: Every saved match already identifies the logging player's hero and result. This keeps statistics accurate after every save and relaunch while following the project rule to calculate statistics from underlying match contributions.

**Alternatives considered**:

- Store per-hero running totals: rejected because it duplicates match data and can become inconsistent.
- Add a database aggregate query: rejected because the existing query is sufficient for this small solo-test list and a new query does not improve the player outcome.

## Decision 2: Exclude opponent-only heroes

**Decision**: A hero record is created only for `playerHeroName`, never for `opponentHeroName`.

**Rationale**: The Heroes page is a personal record of heroes the logging player has played, as defined in the product requirements.

## Decision 3: Use alphabetical order and existing whole-percent rounding

**Decision**: Order records by hero name alphabetically and use the existing whole-percent rounding rule for win rate.

**Rationale**: Alphabetical order is predictable for a first list, while the existing rounding rule gives the Profile and Heroes views consistent language.
