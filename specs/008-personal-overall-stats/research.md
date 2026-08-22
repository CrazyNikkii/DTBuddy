# Research: Personal Overall Statistics

## Decision 1: Calculate the summary from the existing completed-match query

**Decision**: Read the saved completed matches through the existing local repository and derive games, wins, losses, and win rate in memory.

**Rationale**: The current Room query already returns every completed local match. The summary is small, remains accurate after every save and relaunch, and follows the project rule to calculate statistics from underlying match contributions.

**Alternatives considered**:

- Store running totals beside matches: rejected because it creates duplicate data that can become inconsistent.
- Add a special Room aggregate query: rejected for this four-value first view because the existing read is sufficient and adding a query does not improve the user outcome.

## Decision 2: Treat `Player` as a win and `Opponent` as a loss

**Decision**: The winner value already stored with each match is interpreted from the logging player's perspective: `Player` is a win and `Opponent` is a loss.

**Rationale**: This matches the existing history wording and the recorded match model.

**Alternatives considered**:

- Recalculate the winner from hero names: rejected because winner is already an explicit saved match value and hero names do not identify the player role outside the saved fields.

## Decision 3: Use a 0% empty-state rate and whole-percent rounding

**Decision**: Display 0% when no games exist; otherwise show wins divided by games played, rounded to the nearest whole percentage.

**Rationale**: It avoids an undefined divide-by-zero display and keeps the Profile overview easy to scan.

**Alternatives considered**:

- Hide the rate before the first match: rejected because the four-value summary should remain structurally consistent.
- Show decimal places: deferred until a later statistics presentation needs them.
