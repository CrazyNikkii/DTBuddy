# Data Model: Select Match Date

## Date played

The calendar date selected for an unfinished match. It has no time of day or time-zone conversion.

## Unfinished match choices

| Field | Required | Rules |
|---|---:|---|
| Logging-player hero | No, until chosen | Existing canonical roster hero. |
| Opponent hero | No, until chosen | Existing canonical roster hero, including the same hero. |
| Winner | No, until chosen | Logging player or opponent; changing a hero clears it and all later choices. |
| First player | No, until chosen | Logging player or opponent; changing a hero or winner clears it and the date. |
| Date played | No, until date step opens | Defaults to today's local calendar date; changing an earlier answer clears it. |

The five choices stay temporarily in the screen's ViewModel. They are not a Room entity, a draft, or a completed match.

## State transitions

`Choose logging-player hero` → `Choose opponent hero` → `Choose winner` → `Choose first player` → `Choose date played` → `Show five-choice summary`

Back returns to the preceding step. Changing a hero clears winner, first player, and date. Changing the winner clears first player and date. Changing first player clears date.
