# UI Contract: Personal Hero Turn-Order Detail

## Heroes list action

Each existing hero row remains labelled with its hero name and summary values. Selecting the row opens detail for that hero.

## Hero detail

The page shows the selected hero's name as its heading and presents these three labelled records:

1. **Overall**
2. **You went first**
3. **Opponent went first**

Every record contains:

| Label | Meaning |
| --- | --- |
| Games played | Completed local matches represented by the record. |
| Wins | Represented matches won by the player. |
| Losses | Represented matches lost by the player. |
| Win rate | Whole-number percentage of represented matches won by the player. |

Both turn-order records remain visible even when they contain no matches. A Back action returns to Heroes; Android system Back does the same.

## Data refresh

The page loads fresh values from saved device-local matches whenever it opens.

## Exclusions

This increment does not add matchup results, charts, sorting controls, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, global/community statistics, or online features.
