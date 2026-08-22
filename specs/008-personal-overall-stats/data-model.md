# Data Model: Personal Overall Statistics

## Existing source entity: Completed local match

The existing completed match remains unchanged.

| Field | Role in summary |
| --- | --- |
| `winner` | `Player` contributes one win; `Opponent` contributes one loss. |
| Match record | Every record contributes one game played. |

No new database entity, field, or migration is needed.

## Derived entity: Personal overall summary

| Value | Rule |
| --- | --- |
| Games played | Count of all completed local matches. |
| Wins | Count of matches whose winner is `Player`. |
| Losses | Count of matches whose winner is `Opponent`. |
| Win rate | `wins / games played`, rounded to the nearest whole percentage; 0% when games played is zero. |

## Validation rules

- Every completed local match contributes to exactly one of wins or losses.
- Wins plus losses equals games played.
- The summary is derived only and is never persisted separately.
