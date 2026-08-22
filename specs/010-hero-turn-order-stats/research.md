# Research: Personal Hero Turn-Order Detail

## Decision 1: Derive the selected hero's records from saved matches

**Decision**: Read completed local matches through the existing repository, filter by the selected logging-player hero, and calculate one overall record plus first-player and second-player records in memory.

**Rationale**: Saved matches already contain the player's hero, result, and first-player choice. This preserves a single source of truth and refreshes naturally after saving or reopening the app.

**Alternatives considered**:

- Store running turn-order totals: rejected because duplicate statistics can become inconsistent with match records.
- Add a database aggregate query: rejected because the existing small offline history query is adequate for this solo-test slice.

## Decision 2: Partition matches by the logging player's turn order

**Decision**: A match belongs to the first-player record only when its stored first player is `Player`; otherwise it belongs to the second-player record.

**Rationale**: The saved value is expressed from the logging player's viewpoint, so the two displayed records are mutually exclusive and cover every selected-hero match.

**Alternatives considered**:

- Show only the turn order that has games: rejected because zero-game sections make the complete split clear and predictable.

## Decision 3: Reuse the existing Heroes-to-Profile navigation boundary

**Decision**: Selecting a hero row opens its detail within the existing Profile destination; the page Back action and system Back return to Heroes.

**Rationale**: This keeps the new work focused and follows the established local Profile navigation without expanding main navigation.
