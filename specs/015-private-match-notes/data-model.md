# Data Model: Private Match Notes

## Completed local match

Existing fields remain unchanged. Add:

- `note`: nullable plain text, stored only for the match it belongs to.

## Validation and lifecycle

- A player may enter at most 500 characters.
- Input is trimmed before saving; an empty result becomes `null`.
- A new match saves its note together with the existing match values.
- Editing replaces or clears only the selected match's note when the player chooses Save changes.
- Deleting a match deletes its note with that match.
- Notes never contribute to statistics or history ordering.

## Migration

- Existing version-1 records gain the nullable `note` field with `null`, preserving every existing match and its order.
