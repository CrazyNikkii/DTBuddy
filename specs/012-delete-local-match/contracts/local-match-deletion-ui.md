# Local Match Deletion UI Contract

## Entry point

Every row in a populated Match history has a clearly labelled **Delete match** action. Empty history has no deletion action.

## Confirmation

Choosing **Delete match** opens a confirmation that shows:

- the played date;
- the player's hero;
- the opponent's hero; and
- Won or Lost from the player's perspective.

The confirmation has a **Cancel** action and a clearly destructive **Delete match** action.

## Outcomes

- **Cancel** closes the confirmation without changing history or statistics.
- **Delete match** permanently removes exactly the identified local match and refreshes the visible Match history.
- The remaining existing Profile views calculate their values from the remaining saved matches whenever opened or reloaded.

## Exclusions

The contract does not add match editing, undo, match details, notes, favourites, accounts, linked opponents, requests, global/community statistics, or network behaviour.
