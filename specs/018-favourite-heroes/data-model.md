# Data Model: Favourite Heroes

## FavouriteHero

| Field | Meaning | Rules |
|---|---|---|
| `heroName` | Canonical name of a selected Milestone 1 hero | Primary key; must exist in `HeroCatalog.all` |
| `position` | Fixed favourite slot | Unique, zero-based, contiguous; 0 = first, 1 = second, 2 = third |

## Relationships

- A device-local owner has zero to three `FavouriteHero` records.
- A favourite refers to a canonical roster hero only; it does not create, change, or reference a match.

## Validation and state changes

- Set rejects an unknown hero, duplicate hero, out-of-range slot, or a later slot before earlier slots are filled.
- Set replaces one filled slot or fills the next available slot; it does not reorder other slots.
- Migration creates an empty favourites table for version-2 installations; matches and notes remain unchanged.
