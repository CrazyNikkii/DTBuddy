# Data Model: Personal Hero Statistics List

## Existing source entity: Completed local match

The existing completed match remains unchanged.

| Field | Role in a hero record |
| --- | --- |
| `playerHeroName` | Determines the record to which the match contributes. |
| `winner` | `Player` contributes one win; `Opponent` contributes one loss. |
| Match record | Contributes one game played. |

No new database entity, field, or migration is needed.

## Derived entity: Personal hero record

| Value | Rule |
| --- | --- |
| Hero | The logging player's selected hero. |
| Games played | Count of completed local matches logged with that hero. |
| Wins | Count of those matches whose winner is `Player`. |
| Losses | Count of those matches whose winner is `Opponent`. |
| Win rate | `wins / games played`, rounded to the nearest whole percentage. |

## Validation rules

- Every completed local match contributes to exactly one hero record, identified by its logging-player hero.
- A hero record's wins plus losses equals its games played.
- Opponent heroes do not create personal hero records.
- Derived records are ordered alphabetically by hero name and are never persisted separately.
