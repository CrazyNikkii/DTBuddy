# Data Model: Edit Local Match

## Existing completed local match

The existing completed local match remains the single stored record. It has:

- an internal record identity;
- the logging player's hero and opponent hero;
- winner and first-player choices;
- the calendar date played; and
- the original time it was saved, used only for history ordering ties.

No schema fields are added for editing. An edit changes only the five match choices while retaining the record identity and original saved time.

## Temporary edit draft

The current match-selection state becomes an edit draft when it carries the selected record's identity. It begins with the saved choices and follows the existing selection validation.

### Transitions

1. A history row starts an edit draft for its selected record.
2. The player may revise the draft through the existing guided choices.
3. Saving replaces that identified record's editable values and refreshes history.
4. Leaving the flow without saving discards the draft and leaves stored data untouched.

## Derived records

Overall, hero, matchup, and turn-order statistics remain derived directly from all completed local matches. They have no independent stored totals and need no edit-specific state.
