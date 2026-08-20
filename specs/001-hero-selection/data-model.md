# Data Model: Hero Selection

## Hero

Represents one canonical entry in the frozen Milestone 1 roster.

| Field | Meaning | Rules |
|-------|---------|-------|
| `name` | Hero name shown to the player | Non-empty; unique across the catalog; uses the spelling in PROJECT.md. |
| `group` | Browse category | Exactly one of Dice Throne, Marvel, X-Men, or Standalone or promo. |

## Hero group

The category used in grouped browsing.

| Group | Included source roster families |
|-------|-------------------------------|
| Dice Throne | Season 1 and Season 2 |
| Marvel | Marvel |
| X-Men | X-Men |
| Standalone or promo | Santa vs Krampus, Standalone, Outcasts, and Vanguard |

## Validation rules

- The catalog contains exactly 45 heroes.
- Every hero name is unique.
- Every hero has a valid browse group.
- A non-blank search compares the trimmed query to each hero name without considering capitalization.
