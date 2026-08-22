# DTBuddy — Working Product Notes

## Current idea

A mobile app for Dice Throne players to log completed games and view
statistics based on those games.

Android is the initial target.

This is a working product brief. Confirmed sections guide implementation;
items not marked confirmed remain open for product discovery and may change.

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
- Use one device-local owner profile. It requires no sign-in, has no public
  identity, and keeps its matches and statistics on that device. Private notes
  are added in the later Milestone 1 increment defined below.
- View match history and overall, per-hero, per-matchup, and first-player /
  second-player statistics.
- Use the same main app information architecture planned for public launch. The
  home screen is the global-statistics discovery view and has a prominent Log
  match action. During the solo test, it clearly indicates that community
  statistics are not yet available; the personal view remains local-only.
- Edit and delete logged matches.
- Support the frozen 45-hero PvP roster defined below from the start.
- Exclude Dice Throne Adventures enemies, bosses, and mission-only content.
- Treat each hero as one canonical entry for the initial release. Exact
  printing/version selection and version-specific statistics are deferred
  until after public launch.
- Match logging uses a short guided flow: logging player's hero, opponent's
  hero, result and first player, then date and review/save. The player can go
  back to correct earlier selections.
- The initial core build's hero selection offers browse and search.
- The browse view groups heroes by release family: Dice Throne, Marvel, X-Men,
  and standalone or promo heroes. Search covers the full roster.
- The initial core build has no accounts, opponent identities, notes, requests,
  community statistics, or public profiles.
- After the initial core solo build is validated, the later Milestone 1
  solo-test increment adds optional private match notes and favourite-hero
  selection. The player can select and order up to three favourites; hero
  selection then shows those favourites alongside browse and search. Both are
  required before Milestone 1 is complete, but neither is required for the
  initial core build.

### Milestone 1 hero roster

The project list below is the authoritative Milestone 1 roster, frozen as of
2026-08-20. It was reconciled using the [Dice Throne Wiki hero list](https://dice-throne.fandom.com/wiki/Heroes)
and the official Dice Throne site. Each name is one canonical hero entry;
alternate printings and balance versions are not separate entries. A later hero
release does not change this roster without an explicit product decision.

- **Season 1:** Barbarian, Moon Elf, Pyromancer, Shadow Thief, Monk, Paladin,
  Ninja, Treant.
- **Season 2:** Gunslinger, Samurai, Huntress, Tactician, Cursed Pirate,
  Artificer, Seraph, Vampire Lord.
- **Marvel:** Black Panther, Black Widow, Captain Marvel, Doctor Strange,
  Scarlet Witch, Loki, Miles Morales Spider-Man, Thor.
- **X-Men:** Cyclops, Gambit, Iceman, Jean Grey, Psylocke, Rogue, Storm,
  Wolverine.
- **Santa vs Krampus:** Santa, Krampus.
- **Standalone:** Deadpool, Alchemist, Mystic Brawler.
- **Outcasts:** Headless Horseman, Necromancer, Pale Lady, Raveness.
- **Vanguard:** Forgemaster, Druid, Duelist, Sun Elf.

### Milestone 2: invited internal-test scope

- Google sign-in and unique public display-name setup.
- Public-player profile browsing.
- Linked-opponent search and linked-match confirmation.
- The Requests area, including match confirmations and linked-match edit or
  deletion proposals.
- App-wide community statistics based on eligible completed matches.
- This milestone is tested with an invited group of friends before public
  launch.

## Confirmed main navigation

- The app's primary navigation uses four destinations: **Log match**,
  **Requests**, **Profile**, and **Global stats**. The Log match destination is
  the initial selected destination during the solo test and makes starting a
  match quick. Global stats is the global hero-statistics discovery view.
- Requests is shown when linked-match requests are available in Milestone 2;
  it shows a badge/count for pending confirmations and change requests. It is
  not a functional solo-test feature.
- My profile contains the player's overview and links to their Heroes and
  Match history pages.
- Player search is added with multi-user features so players can find and open
  public profiles.
- The navigation structure is established in milestone 1 and expanded as the
  related features become available, avoiding a later UI/UX redesign.

### Confirmed back-navigation behaviour

- Android's system Back gesture and Back button return the player to the
  immediately preceding screen, continuing one screen at a time through the
  navigation path.
- If leaving a screen would abandon unsaved player input, the app asks for
  confirmation before discarding it. Choosing to keep editing leaves the draft
  unchanged; choosing to discard makes no saved-data change.
- The app exits only when the player uses Back from the main-menu root and no
  earlier screen remains. Until the planned main-menu screen is introduced,
  the selected primary navigation destination with no nested screen is the
  temporary root for this behaviour.

## Confirmed visual direction

- DTBuddy has an original, dark, tactile tabletop-inspired visual style. Its
  base colours are charcoal and blue-grey, with fiery orange and warm-gold
  accents used deliberately for primary actions, selected navigation, and
  small attention indicators such as pending-request badges.
- The interface feels dramatic but calm and remains easy to read: generous
  breathing room, high-contrast text, clear hierarchy, and one prominent
  primary action per screen where appropriate.
- The app uses original UI components, placeholder branding until a DTBuddy
  logo is supplied, and original icons. It must not copy Dice Throne logos,
  artwork, cards, dice, typography, or other distinctive visual assets.

## Original product ideas

Later confirmed sections override these early notes.

- Log completed matches
- Track personal win/loss statistics
- Hero-specific statistics
- Matchup-specific statistics
- Community statistics
- Registered users / opponents
- The app going to Google Play

## Confirmed non-goal

- No live-game companion / HP tracker / Dice roller.

## Confirmed intellectual-property and public-release posture

- DTBuddy is an unofficial, fan-made match-tracking application and must state
  clearly that it is not affiliated with, endorsed by, or sponsored by Dice
  Throne Inc. or other relevant rights holders.
- The product name and primary branding do not use the Dice Throne name.
- The app may use hero names as necessary to identify the heroes that players
  select and record. It uses original app visuals and must not copy official
  logos, character artwork, cards, boards, dice, or rules text. Broad visual
  inspiration must not reproduce recognisable official artwork or branding.
- Before any Google Play release, the product owner will contact Dice Throne
  Inc. to request written permission or guidance for the planned public use of
  relevant names and fan-project presentation. The release decision follows
  that response.

## Confirmed initial-launch scope

- Completed matches are logged as 1v1 only. Multiplayer and team formats
  are deferred.

## Confirmed account and community-statistics approach

- At public launch, the app opens to a sign-in screen, with an **Explore as
  guest** option.
- Guests can browse all-time, app-wide hero statistics only. They cannot log
  matches or access personal features.
- At public launch, personal match logging and personal statistics require an
  account.
- At launch, accounts use **Continue with Google** only.
- Each account has a unique public display name, chosen by the player. It is
  the initial way to find and link an opponent's account.
- Players can change their display name.
- Every eligible completed match logged in the app contributes to app-wide
  statistics automatically.
- The initial version has no special-rules or exclusion-from-community-stats
  option.
- Users can request deletion of their account and associated data. Deleted
  accounts, profiles, sessions, notes, and match records are removed from the
  app. Before removal, each deleted match's anonymous statistical contribution
  remains in app-wide totals, with no retained player identity, account link,
  individual match record, or match date. App-wide statistics therefore reflect
  every eligible match ever logged while personal statistics no longer include
  deleted matches.
- The release requires a public privacy policy, a Google Play Data Safety
  declaration, and an accessible account-deletion route outside the app.

## Confirmed profiles and statistics views

- Player profiles are public. A profile shows a player's games played, overall
  win rate, selected favourite hero, owned heroes, hero statistics, and
  eligible completed-match history.
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
- Match history shows eligible completed matches newest played date first,
  with creation time descending as the tie-breaker. It shows the heroes,
  result, and a linked opponent's display name when one exists. An unlinked
  opponent is displayed as **Unknown**. Opening a match shows its detailed
  statistics.
- A match detail can show each player's record with their selected hero,
  each player's record against the opposing hero, their specific head-to-head
  hero matchup, and the global hero matchup record.
- Global statistics follow the same browse-and-drill-down pattern as player
  statistics.
- Displayed statistics include the eligible completed match currently being
  viewed.

## Confirmed initial statistics focus

- The initial statistics set focuses on games played, wins, losses, and win
  rate overall, per hero, per hero matchup, and by first-player / second-player
  position.
- Linked players can also view their confirmed head-to-head record.
- App-wide statistics provide the same hero, matchup, and turn-order views
  using eligible completed matches across the app.
- Profile and global overviews highlight the overall record, favourite hero,
  and most-played heroes. Hero details provide the hero record, turn-order
  splits, and a matchup table.
- Match details retain the selected match's contextual personal, head-to-head,
  and global matchup records.
- ELO, player rankings / leaderboards, win streaks, deep charts, and best/worst
  opponent analysis are deferred beyond the initial launch.
- When global hero win rates are ranked, sorting uses a transparent Bayesian
  weighted win rate rather than raw win rate or a minimum-games cutoff. The
  adjusted rate is `(wins + 5) / (games played + 10)`: a neutral 50% prior with
  the weight of 10 games. The displayed result always includes the actual win
  rate and games played, and explains that the ranking is adjusted for sample
  size.
- Global hero lists default to adjusted win-rate order and can also be sorted
  by raw win rate, games played, or hero name.

## Confirmed linked-match workflow

- An unlinked match is eligible immediately: it counts in the logging player's
  statistics, appears in their public history with the opponent shown as
  **Unknown**, and contributes to app-wide statistics.
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
