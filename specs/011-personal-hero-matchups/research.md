# Research: Personal Hero Matchup Table

## Decision 1: Derive matchup records from saved matches

**Decision**: Read completed local matches through the existing repository, filter to the selected logging-player hero, group those matches by opponent hero, and calculate each record in memory.

**Rationale**: Each saved match already contains all required values. Keeping matches as the single source of truth prevents a second stored set of statistics from getting out of sync.

**Alternatives considered**:

- Store running matchup totals: rejected because duplicated totals can disagree with saved match history.
- Add a new database aggregate query: rejected because the existing small offline history query is adequate for this solo-test increment.

## Decision 2: Order matchup rows alphabetically by opponent hero

**Decision**: Sort the derived rows by opponent hero name.

**Rationale**: Alphabetical order is predictable and matches the existing personal Heroes list. It avoids adding a sorting control before it is needed.

**Alternatives considered**:

- Sort by win rate or games played: rejected because those views are not yet part of the approved increment and would require a product decision on ranking ties.

## Decision 3: Extend the existing hero-detail model

**Decision**: Add matchup records to the existing selected-hero detail returned by the repository and displayed by the existing hero-detail page.

**Rationale**: The player already reaches a hero detail to see overall and turn-order records. Adding the related matchup breakdown there avoids new navigation and keeps the user journey short.
