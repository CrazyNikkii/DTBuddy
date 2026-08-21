# Data Model: Save Local Match

## Completed local match

| Field | Meaning | Validation |
|---|---|---|
| `id` | Local record identifier | Generated only after a successful insert. |
| `playerHeroName` | The logging player's selected canonical hero name | Required; comes from the frozen catalogue. |
| `opponentHeroName` | The opposing selected canonical hero name | Required; comes from the frozen catalogue. |
| `winner` | Whether the player or opponent won | Required; one of the two participants. |
| `firstPlayer` | Whether the player or opponent went first | Required; one of the two participants. |
| `datePlayed` | Selected calendar date | Required; stored as a date only. |
| `createdAt` | When the record was saved locally | Generated at save time; supports the later history tie-breaker. |

## Relationships

- A completed local match belongs only to the app's implicit device-local owner.
- It has no player account, opponent identity, linked-match state, note, or remote copy.

## State transitions

1. The existing guided flow collects temporary choices.
2. The player selects Save match only after all five required choices exist.
3. One completed local match is created and persisted.
4. The app displays a success confirmation.
5. Starting another log discards the temporary choices and returns to the opening hero step; it never removes the saved record.
