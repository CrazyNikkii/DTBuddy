# Data Model: Select Match Heroes

## Hero choice

The existing `Hero` catalog item is selected as either the logging player's hero or opponent's hero. It remains canonical and comes only from the frozen roster.

## Unfinished match choices

| Field | Required | Rules |
|---|---:|---|
| Logging-player hero | No, until chosen | Any catalog hero; retained when returning from opponent selection. |
| Opponent hero | No, until chosen | Any catalog hero, including the same hero as the logging player. |

The choices are held temporarily by the screen's ViewModel, including across ordinary activity recreation. They are not a Room entity and are not a completed match.

## State transitions

`Choose logging-player hero` → `Choose opponent hero` → `Show both choices`

From `Choose opponent hero`, Back returns to `Choose logging-player hero` and clears only the opponent selection if one has not been confirmed.
