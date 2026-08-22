# Data Model: Personal Hero Turn-Order Detail

## Existing source entity: Completed local match

The existing completed match remains unchanged.

| Field | Role in selected hero detail |
| --- | --- |
| `playerHeroName` | Selects the hero's matches. |
| `winner` | `Player` contributes a win; `Opponent` contributes a loss. |
| `firstPlayer` | Places the match in the first-player or second-player record. |

No new database entity, field, or migration is needed.

## Derived entity: Turn-order record

| Value | Rule |
| --- | --- |
| Games played | Count of contributing matches. |
| Wins | Count whose winner is `Player`. |
| Losses | Count whose winner is `Opponent`. |
| Win rate | `wins / games played`, rounded to the nearest whole percentage; 0% for zero games. |

## Derived entity: Personal hero turn-order detail

| Record | Contributing matches |
| --- | --- |
| Overall | Every completed local match where `playerHeroName` is the selected hero. |
| You went first | Selected-hero matches where `firstPlayer` is `Player`. |
| Opponent went first | Selected-hero matches where `firstPlayer` is `Opponent`. |

## Validation rules

- A displayed selected hero is one already present in the personal Heroes list.
- Every selected-hero match contributes once to the overall record and exactly once to one turn-order record.
- In every record, wins plus losses equals games played.
- The first-player and second-player game totals sum to the overall game total.
- Derived values are never persisted separately.
