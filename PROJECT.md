# DTBuddy — Working Product Notes

## Current idea

A mobile app for Dice Throne players to log completed games and view
statistics based on those games.

Android is the initial target.

This is still in product discovery. Nothing here should be treated as a
complete specification yet.

## Confirmed delivery milestones

- Development progresses through three milestones:
  1. A solo test build used by the product owner. It validates personal match
     logging, match history, and personal statistics before any social or
     public features are added.
  2. An invited internal / friends-only test build. It introduces multiple
     accounts and validates linked matches, requests, shared statistics, and
     the multi-user experience with a small trusted group.
  3. A public Google Play launch build. It is the complete, working first
     version for guests and registered players, rather than an early test
     release.
- Each earlier milestone can be deliberately narrow; public-launch
  requirements are completed for the public launch build.

### Milestone 1: solo test scope

- Log completed, unlinked 1v1 matches with date played, both heroes, winner,
  and first player.
- View match history and overall, per-hero, per-matchup, and first-player /
  second-player statistics.
- Use the same main app information architecture planned for public launch. The
  home screen is the global-statistics discovery view and has a prominent Log
  match action. During the solo test, it clearly indicates that community
  statistics are not yet available; the personal view remains local-only.
- Edit and delete logged matches.
- Support every official playable PvP hero from the start, including base-set,
  Marvel, X-Men, and official promo or standalone heroes.
- Exclude Dice Throne Adventures enemies, bosses, and mission-only content.
- Treat each hero as one canonical entry for the initial release. Exact
  printing/version selection and version-specific statistics are deferred
  until after public launch.
- Match logging uses a short guided flow: logging player's hero, opponent's
  hero, result and first player, then date and review/save. The player can go
  back to correct earlier selections.
- Before favourite-hero selection is implemented, hero selection offers browse
  and search. Once implemented, it also offers the player's up-to-three
  favourites alongside browse and search.
- The browse view groups heroes by release family: Dice Throne, Marvel, X-Men,
  and standalone or promo heroes. Search covers the full roster.
- This milestone has no accounts, opponent identities, notes, requests,
  community statistics, or public profiles.
- After the initial core solo build is validated, add optional private notes to
  matches during the solo-test phase, before milestone 2 begins. Notes are not
  required for the first solo-test build.

### Milestone 2: invited internal-test scope

- Google sign-in and unique public display-name setup.
- Public-player profile browsing.
- Linked-opponent search and linked-match confirmation.
- The Requests area, including match confirmations and linked-match edit or
  deletion proposals.
- App-wide community statistics based on confirmed matches.
- This milestone is tested with an invited group of friends before public
  launch.

## Confirmed main navigation

- Home is the global hero-statistics discovery view.
- My profile contains the player's overview and links to their Heroes and
  Match history pages.
- Player search is added with multi-user features so players can find and open
  public profiles.
- Requests is added with linked matches and shows pending confirmations and
  change requests.
- The navigation structure is established in milestone 1 and expanded as the
  related features become available, avoiding a later UI/UX redesign.

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
  win rate, selected favourite hero, owned heroes, hero statistics, and
  confirmed match history.
- Google identity and private match notes are never shown publicly.
- The profile overview links to separate **Heroes** and **Match history**
  pages.
- The Heroes page lists every hero the player has played, with games played
  and win rate. It is not limited to the heroes they own.
- Opening a hero shows detailed statistics, including matchup results and
  first-player / second-player splits.
- Owned heroes are a profile collection only; they never restrict which hero
  a player can use when logging a match.
- Players manually add, remove, and change the heroes in their owned-heroes
  collection. It has no limit and is separate from their favourite heroes.
- A player selects and orders up to three favourite heroes. It is not inferred
  from games played or win rate. Their first-ranked favourite is shown on their
  public profile.
- Match history shows completed matches in date order, with heroes, result,
  and a linked opponent's display name when one exists. Opening a match shows
  its detailed statistics.
- A match detail can show each player's record with their selected hero,
  each player's record against the opposing hero, their specific head-to-head
  hero matchup, and the global hero matchup record.
- Global statistics follow the same browse-and-drill-down pattern as player
  statistics.
- Displayed statistics include the confirmed match currently being viewed.

## Confirmed initial statistics focus

- The initial statistics set focuses on games played, wins, losses, and win
  rate overall, per hero, per hero matchup, and by first-player / second-player
  position.
- Linked players can also view their confirmed head-to-head record.
- App-wide statistics provide the same hero, matchup, and turn-order views
  using confirmed matches across the app.
- Profile and global overviews highlight the overall record, favourite hero,
  and most-played heroes. Hero details provide the hero record, turn-order
  splits, and a matchup table.
- Match details retain the selected match's contextual personal, head-to-head,
  and global matchup records.
- ELO, player rankings / leaderboards, win streaks, deep charts, and best/worst
  opponent analysis are deferred beyond the initial launch.
- When global hero win rates are ranked, sorting uses a transparent Bayesian
  weighted win rate rather than raw win rate or a minimum-games cutoff. The
  displayed result always includes the actual win rate and games played, and
  explains that the ranking is adjusted for sample size.
- Global hero lists default to adjusted win-rate order and can also be sorted
  by raw win rate, games played, or hero name.

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

## Confirmed friends-system approach

- A friends list is a planned future feature, but it is not part of the first
  launch.
- Public profiles and display-name search provide the initial way for players
  to find and link opponents.
- Revisit the friends-system design after launch, informed by adoption and
  recurring-opponent usage.

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
