# UI Contract: Private Match Notes

## Match review

- Shows an optional field labelled `Private note (optional)` after the existing selected match values.
- Accepts up to 500 characters.
- Saving a blank field creates a match without a note.

## Edit overview

- Shows the same optional note field with the selected match's saved note.
- The player may add, replace, or clear the value.
- Changes are draft-only until `Save changes`.

## Match history

- A match with a note shows `Private note: {note}` in its row.
- A match without a note shows no note label or blank area.
- Notes have no interaction with sorting, statistics, edit, or delete actions.
