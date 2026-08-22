# UI Contract: Personal Overall Statistics

## Profile summary

When Profile opens, it presents the existing local-device explanation followed by a clearly labelled personal overall summary containing these values:

| Label | Meaning |
| --- | --- |
| Games played | Count of completed matches saved on this device. |
| Wins | Saved matches the player won. |
| Losses | Saved matches the player lost. |
| Win rate | Whole-number percentage of saved matches the player won. |

The existing **Match history** action remains available below the summary.

## States

- **No saved matches**: Show 0 games played, 0 wins, 0 losses, and 0% win rate.
- **Saved matches**: Show the calculated values from all completed local matches.

## Exclusions

This screen does not show hero, matchup, turn-order, global, or community statistics; charts; account or public-profile information; or actions to edit or delete a match.
