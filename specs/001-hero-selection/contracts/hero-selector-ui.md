# Hero Selector UI Contract

## Inputs

- A fixed catalog of canonical heroes.
- Optional search text entered by the player.

## Behaviour

- With blank search text, display the catalog grouped by its four browse categories.
- With non-blank search text, display only heroes whose names contain the trimmed query without considering capitalization.
- With a non-blank query that matches nothing, display an empty-state message.

## Outputs

- The player can see hero names and their browse grouping.
- This version exposes no saved selection or action result.
