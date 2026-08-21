# UI Contract: Save Local Match Flow

## Summary destination

**Precondition**: Player hero, opponent hero, winner, first player, and date played are all present.

**Required content**:

- The five selected values, as the current summary shows.
- A `Save match` action.
- Existing Back behaviour, which changes only temporary choices and does not save a match.

**Save behaviour**:

- The action saves one completed local match.
- While saving, it cannot be activated again.
- On success, the app opens the saved-match confirmation.

## Saved-match confirmation destination

**Required content**:

- Clear text that the completed match was saved on this device.
- A `Log another match` action.

**Log another match behaviour**:

- Clears temporary guided-flow choices.
- Opens `Choose your hero`.
- Does not display history or statistics.

## Safe restoration

If Android opens either destination without all required temporary choices, navigation returns to `Choose your hero` and no match is saved.
