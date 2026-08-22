# Data Model: Personal Hero Matchup Table

## Existing source entity: Completed local match

The existing completed match remains unchanged.

| Field | Role in a matchup record |
| --- | --- |
| `playerHeroName` | Selects matches for the hero detail being viewed. |
| `opponentHeroName` | Groups selected-hero matches into opponent-specific rows. |
| `winner` | `Player` contributes a win; `Opponent` contributes a loss. |

No new database entity, field, or migration is needed.

## Derived entity: Personal hero matchup record

| Value | Rule |
| --- | --- |
| Opponent hero | One distinct opponent hero from selected-hero matches. |
| Games played | Count of selected-hero matches against that opponent. |
| Wins | Count whose winner is `Player`. |
| Losses | Count whose winner is `Opponent`. |
| Win rate | `wins / games played`, rounded to the nearest whole percentage. |

## Relationships and validation rules

- A selected hero detail includes zero or more matchup records.
- Every selected-hero match contributes to exactly one matchup record, based on its opponent hero.
- In every record, wins plus losses equals games played.
- Matchup records are ordered alphabetically by opponent hero name.
- Matchup records are derived only and are never persisted separately.
- The selected hero's overall record is not calculated by adding matchup rows; both views instead derive from the underlying matches.
