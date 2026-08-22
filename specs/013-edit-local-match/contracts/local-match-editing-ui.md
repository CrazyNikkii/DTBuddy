# Local Match Editing UI Contract

## Start editing

Every saved row in Match history has an **Edit match** action. Choosing it opens the existing guided match-log flow for that record.

## Guided choices and review

The flow begins with the selected record's current player hero, opponent hero, winner, first player, and date. The player can retain or revise each choice, use Back to revisit earlier choices, then review the edited details. The final action is labelled **Save changes**.

## Result

After saving, the app confirms that the match changes were saved. Match history shows exactly one updated selected row; it does not show an extra row.

## Leaving without saving

The player can leave using existing navigation without a special confirmation. Because no save happened, the stored record is unchanged.

## Scope boundary

This contract does not add match notes, undo, historical versions, accounts, linked opponents, requests, global/community statistics, or network behaviour.
