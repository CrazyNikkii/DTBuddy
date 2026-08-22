# UI Contract: Personal Hero Matchup Table

## Hero detail

Below the existing Overall, **You went first**, and **Opponent went first** records, the selected hero detail shows a **Matchups** section.

Each matchup row contains:

| Label | Meaning |
| --- | --- |
| Opponent hero | The hero faced by the selected player hero. |
| Games played | Completed local matches represented by the row. |
| Wins | Represented matches won by the player. |
| Losses | Represented matches lost by the player. |
| Win rate | Whole-number percentage of represented matches won by the player. |

Rows are ordered alphabetically by opponent hero name. The section is shown whenever a selected hero detail is shown; a detail reached from the existing Heroes list normally has at least one matchup row.

## Data refresh

The hero detail loads fresh values from saved device-local matches whenever it opens.

## Exclusions

This increment does not add global/community statistics, charts, sorting controls, favourites, notes, editing, deletion, accounts, public profiles, linked opponents, requests, or online features.
