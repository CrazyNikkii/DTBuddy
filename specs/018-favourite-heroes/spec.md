# Feature Specification: Favourite Heroes

**Feature Branch**: `codex/favourite-heroes`

**Created**: 2026-08-22

**Status**: Approved for implementation

**Input**: User-approved next Milestone 1 increment: choose and order up to three local favourite heroes, then show them first when selecting a hero for a match.

## User Scenarios & Testing

### User Story 1 - Choose favourite heroes (Priority: P1)

A solo player can choose up to three heroes in fixed first, second, and third favourite slots, so the heroes they play most are quicker to find.

**Why this priority**: Favourite selection is explicitly required to complete Milestone 1 and has value even before it is used in the match-log flow.

**Independent Test**: Choose heroes for the first, second, and third slots, replace one slot, and confirm the saved order remains correct after reopening the favourites screen.

**Acceptance Scenarios**:

1. **Given** no favourites are saved, **When** the player opens favourite-hero management, **Then** they can browse and search the complete 45-hero roster and choose up to three heroes.
2. **Given** a favourite slot is filled, **When** the player selects that slot and chooses a different hero, **Then** the new hero replaces only that slot.
3. **Given** the first slot is filled, **When** the player opens favourite management, **Then** they can select the second slot; the third slot becomes available after the second is filled.

---

### User Story 2 - Select a favourite while logging (Priority: P1)

A solo player beginning a new match sees their saved favourites as a dedicated section alongside the existing browse and search choices, and can select one normally.

**Why this priority**: This is the practical benefit of choosing favourites and is required by the approved Milestone 1 scope.

**Independent Test**: Save three ordered favourites, start a new match, select one from the Favourites section, and finish or discard the draft without changing the favourites.

**Acceptance Scenarios**:

1. **Given** favourites are saved, **When** the player opens Choose your hero, **Then** a Favourites section lists those heroes in saved order alongside browse and search.
2. **Given** no favourites are saved, **When** the player opens hero selection, **Then** the existing browse and search experience remains available without an empty Favourites section.
3. **Given** the player selects a favourite, **When** they continue through match logging, **Then** the selected hero is used exactly as if it had been selected through browse or search.

### Edge Cases

- Duplicate favourites are not allowed.
- A player can save fewer than three favourites, including none.
- Existing saved matches, private notes, history, and statistics are unchanged by favourite management.
- Existing device data upgrades without losing saved matches or notes; it begins with no favourites.

## Requirements

### Functional Requirements

- **FR-001**: The app MUST let the local player save zero to three distinct favourite heroes from the frozen Milestone 1 roster and preserve the chosen order.
- **FR-002**: The app MUST provide a local favourites-management screen reachable from the existing personal Profile area.
- **FR-003**: The management screen MUST offer the existing browse and search methods for the full roster.
- **FR-004**: The app MUST let the player choose or replace the hero in each fixed first, second, and third favourite slot, in that order.
- **FR-005**: The app MUST show saved favourites in order as a Favourites section on the Choose your hero screen, alongside the existing browse and search experience. It MUST NOT show favourites on Choose opponent hero.
- **FR-006**: The Favourites section MUST be absent when no favourites are saved.
- **FR-007**: Selecting a favourite MUST follow the existing hero-selection behaviour and MUST NOT alter match data, private notes, history, or statistics.
- **FR-008**: Existing local matches and notes MUST remain available after the feature is added, with no saved favourites assumed for existing installations.
- **FR-009**: The feature MUST remain fully local and offline. It MUST NOT add accounts, opponent identities, owned-hero collections, requests, community statistics, network behaviour, cloud sync, or public profiles.

### Key Entities

- **Favourite hero**: One local roster hero together with its position in the player's ordered list.

## Success Criteria

### Measurable Outcomes

- **SC-001**: In manual testing, a player can choose three favourite slots in under one minute.
- **SC-002**: In automated checks, 100% of attempts to save duplicate or more-than-three favourites leave a valid ordered list of at most three distinct heroes.
- **SC-003**: In manual testing, each saved favourite appears in its chosen order on Choose your hero and is absent from Choose opponent hero.
- **SC-004**: In automated checks, existing match records and their derived statistics are unchanged after adding or changing favourites.

## Assumptions

- Favourites belong only to the single device-local Milestone 1 owner profile.
- The Profile overview is the appropriate local place to manage favourites; this does not create a public profile feature.
- Slots are filled in order; selecting a filled slot replaces its hero without changing the others.
