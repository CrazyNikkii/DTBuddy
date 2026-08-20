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
- Each account has a unique public display name, chosen by the player. It is
  the initial way to find and link an opponent's account.
- Players can change their display name.
- Every standard completed match logged in the app contributes to app-wide
  statistics automatically.
- The initial version has no special-rules or exclusion-from-community-stats
  option.
- Users can request deletion of their account and associated data. Deleted
  matches are removed from both personal and app-wide statistics.
- The release requires a public privacy policy, a Google Play Data Safety
  declaration, and an accessible account-deletion route outside the app.

## Confirmed profiles and statistics views

- Player profiles are public. A profile shows a player's games played, overall
  win rate, favourite hero, owned heroes, hero statistics, and confirmed match
  history.
- Google identity and private match notes are never shown publicly.
- The profile overview links to separate **Heroes** and **Match history**
  pages.
- The Heroes page lists every hero the player has played, with games played
  and win rate. It is not limited to the heroes they own.
- Opening a hero shows detailed statistics, including matchup results and
  first-player / second-player splits.
- Owned heroes are a profile collection only; they never restrict which hero
  a player can use when logging a match.
- Match history shows completed matches in date order, with heroes, result,
  and a linked opponent's display name when one exists. Opening a match shows
  its detailed statistics.
- A match detail can show each player's record with their selected hero,
  each player's record against the opposing hero, their specific head-to-head
  hero matchup, and the global hero matchup record.
- Global statistics follow the same browse-and-drill-down pattern as player
  statistics.
- Displayed statistics include the confirmed match currently being viewed.

## Confirmed linked-match workflow

- An unlinked match counts immediately in the logging player's statistics and
  app-wide statistics.
- A linked match is a pending proposal until the opponent confirms it. Until
  then it is visible only to the logger and does not affect either player's
  statistics, public profile, or app-wide statistics.
- Confirmation makes a linked match public and updates both players'
  statistics and app-wide statistics.
- Either player can propose an edit or deletion of a confirmed linked match.
  The original match remains in effect until the other player confirms the
  request.
- If the other player rejects an edit or deletion request, the original match
  remains unchanged. There is no in-app dispute system.
- The requester can cancel any pending match, edit, or deletion request.
- Unlinked matches can be edited or deleted by the logging player without
  confirmation.
- The initial app includes an in-app **Requests** area with a badge/count for
  pending confirmations and change requests. Push notifications are deferred.

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

When both players use the app, one player can log and link the match, then ask
the opponent to confirm it. See the linked-match workflow above.

## Major open questions

- Do we actually need a friends system?
- What statistics are actually useful?
- What belongs in the first usable version?
