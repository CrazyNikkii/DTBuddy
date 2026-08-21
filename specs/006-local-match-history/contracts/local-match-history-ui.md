# Local Match History UI Contract

## Entry point

The Match saved confirmation provides a **View match history** action. Choosing it opens the local history without clearing or creating temporary match choices.

## Empty history

When no completed local matches exist, show a title identifying Match history and a clear message that no matches have been saved yet.

## Populated history

For every completed local match, show:

- formatted date played;
- logging player's hero;
- opponent's hero; and
- Won or Lost from the logging player's perspective.

Rows are ordered by latest played date, then most recently saved when dates are equal, then the later stored record if the save times also tie. Rows are read-only; selecting, editing, deleting, details, and statistics are outside this contract.
