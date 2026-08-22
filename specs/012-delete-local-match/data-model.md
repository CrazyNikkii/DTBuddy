# Data Model: Delete Local Match

## Completed local match (existing)

| Field | Role in deletion | Rule |
| --- | --- | --- |
| `id` | Selected-record identity | Delete only the row with this primary key. |
| `playerHeroName` | Confirmation context | Show as the player's hero. |
| `opponentHeroName` | Confirmation context | Show as the opponent's hero. |
| `winner` | Confirmation context | Present as Won or Lost from the player's perspective. |
| `datePlayed` | Confirmation context | Present as the match's played calendar date. |

No fields or tables change for this feature.

## Deletion confirmation (temporary screen state)

| Value | Rule |
| --- | --- |
| selected completed local match | Exists only while the confirmation is open and identifies the one record the player asked to delete. |
| cancellation | Clears the temporary choice and leaves stored data unchanged. |
| confirmed deletion | Removes the selected stored record, clears the temporary choice, and reloads Match history. |

## Relationships

- A deletion confirmation refers to exactly one existing completed local match.
- A completed local match contributes to each derived personal-statistics view only while it remains stored.
