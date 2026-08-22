# Favourite-Hero UI Contract

## Profile overview

- Shows a `Favourite heroes` action that opens favourite management.

## Favourite management

- Shows three ordered first, second, and third favourite slots.
- The next empty slot becomes selectable only after the earlier slot is filled; a filled slot can be selected to replace its hero.
- Slot-selection flow uses the existing searchable/browsable canonical hero roster.
- Back returns to Profile overview.

## Match hero picker

- When favourites exist, Choose your hero shows a `Favourites` section before existing browse/search controls, preserving saved order.
- Selecting a favourite invokes the same selection behaviour as a browsed or searched hero.
- No empty Favourites section is displayed when none are saved.
- Choose opponent hero never shows the Favourites section.
