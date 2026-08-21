# Data Model: Local Match History

## Completed local match (existing)

| Field | Used in history | Rule |
| --- | --- | --- |
| `playerHeroName` | Yes | Display as the logging player's hero. |
| `opponentHeroName` | Yes | Display as the opponent's hero. |
| `winner` | Yes | Convert to Won when it is `Player`; otherwise Lost. |
| `datePlayed` | Yes | Display as the played calendar date; primary descending sort. |
| `createdAtMillis` | Yes | Secondary descending sort when played dates tie. |
| `id` | Yes | Final descending sort when both played date and save time tie. |

No fields or tables change for this feature.
