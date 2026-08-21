# UI Contract: Match Date Flow

## Date-played state

- Heading: `When was the match played?`
- It shows the currently selected calendar date and explains that today is preselected.
- The player can open the device date selector to choose a different date.
- A `Continue` action opens the temporary summary.
- Back returns to the first-player choice.

## Temporary summary state

- Heading: `Match details chosen`
- The logging-player hero, opponent hero, winner, first player, and date played are named clearly.
- It explains that review and saving will be added later.
- It offers no save action.

## Safe restoration

- The date step requires both heroes, winner, and first player.
- The summary requires those four choices and a selected date.
- A restored destination missing any prerequisite returns to the opening hero step.
