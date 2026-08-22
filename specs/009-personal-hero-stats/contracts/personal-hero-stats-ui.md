# UI Contract: Personal Hero Statistics List

## Profile action

Profile retains its existing local-device explanation, personal overall summary, and **Match history** action. It also provides a clearly labelled **Heroes** action.

## Heroes list

When the player opens **Heroes**, the page shows a **Heroes** heading and one row for every hero they have logged as their own hero. Each row contains:

| Label | Meaning |
| --- | --- |
| Hero name | The logging player's hero. |
| Games played | Completed matches logged with that hero. |
| Wins | Those matches won by the player. |
| Losses | Those matches lost by the player. |
| Win rate | Whole-number percentage of those matches won by the player. |

Rows appear alphabetically by hero name. The page provides a Back action that returns to Profile.

## States

- **No saved matches**: Show a clear message that no heroes have been played yet.
- **Saved matches**: Show the derived player-hero rows only, refreshed from completed local matches when the page opens.

## Exclusions

This increment does not offer hero details, matchup or turn-order statistics, sorting controls, charts, favourites, notes, match editing or deletion, accounts, public profiles, linked opponents, or online/global/community statistics.
