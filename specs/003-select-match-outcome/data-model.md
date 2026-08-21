# Data Model: Select Match Outcome

## Participant role

One of the two players in the unfinished match: the logging player or the opponent.

## Unfinished match choices

| Field | Required | Rules |
|---|---:|---|
| Logging-player hero | No, until chosen | Existing canonical roster hero. |
| Opponent hero | No, until chosen | Existing canonical roster hero, including the same hero. |
| Winner | No, until chosen | Logging player or opponent; changing a hero clears it. |
| First player | No, until chosen | Logging player or opponent; changing a hero or winner clears it. |

The four choices stay temporarily in the screen's ViewModel. They are not a Room entity and are not a completed match.

## State transitions

`Choose logging-player hero` → `Choose opponent hero` → `Choose winner` → `Choose first player` → `Show four-choice summary`

Back returns to the preceding step. Changing a hero resets winner and first-player choices. Changing the winner resets the first-player choice.
