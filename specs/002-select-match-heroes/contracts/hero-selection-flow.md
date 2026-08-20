# UI Contract: Hero Selection Flow

## Opening state

- Heading: `Choose your hero`
- Supporting text explains this is the first match-log choice.
- Hero rows are selectable.

## Opponent state

- Heading: `Choose opponent hero`
- The selected logging-player hero is visible.
- An in-flow `Back` action and Android system Back return to the opening state.
- Hero rows remain selectable, searchable, and grouped identically.

## Confirmation state

- Heading: `Heroes chosen`
- Both choices are named with clear player labels.
- The UI explicitly says that the rest of match logging comes next, without presenting a save action.
