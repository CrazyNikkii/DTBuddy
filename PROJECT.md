# DTBuddy — Working Product Notes

## Current idea

A mobile app for Dice Throne players to log completed games and view
statistics based on those games.

Android is the initial target.

This is still in product discovery. Nothing here should be treated as a
complete specification yet.

## Things I currently like

- Log completed matches
- Track personal win/loss statistics
- Hero-specific statistics
- Matchup-specific statistics
- Potential community statistics
- Potential registered users / opponents
- The app going to google appstore

## Confirmed non-goal

- No live-game companion / HP tracker / Dice roller.

## Confirmed initial-launch scope

- Completed matches are logged as 1v1 only. Multiplayer and team formats
  are deferred.

## Confirmed account and community-statistics approach

- The app opens to a sign-in screen, with an **Explore as guest** option.
- Guests can browse all-time, app-wide hero statistics only. They cannot log
  matches or access personal features.
- Personal match logging and personal statistics require an account.
- At launch, accounts use **Continue with Google** only.
- Every standard completed match logged in the app contributes to app-wide
  statistics automatically.
- The initial version has no special-rules or exclusion-from-community-stats
  option.

## Confirmed match-log information

Each completed match records:

- The logging player's hero
- The opponent's hero
- The winner
- Who went first
- The date played, prefilled as today but editable

Optional information:

- A linked opponent player, when the opponent uses the app
- A short freeform note from the player logging the match

There is no free-text opponent-name field. If the opponent does not use the
app, no opponent identity is recorded.

When both players use the app, one player should be able to log and link the
match, then ask the opponent to confirm that the match and result are correct.
The detailed confirmation and dispute behavior remains undecided.

## Major open questions

- Do we actually need a friends system?
- What should other users be able to see?
- What statistics are actually useful?
- What belongs in the first usable version?
