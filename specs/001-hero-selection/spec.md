# Feature Specification: Hero Selection

**Feature Branch**: `001-hero-selection`
**Created**: 2026-08-20
**Status**: Approved for implementation
**Input**: User-approved work: create the Android app foundation and a static hero-selection screen.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Find a hero to use in a future match (Priority: P1)

As a Dice Throne player, I can open DTBuddy and browse or search its supported heroes, so that I can quickly find the hero I will later select while logging a match.

**Why this priority**: Choosing a hero is required before DTBuddy can record any match, and this is the smallest useful way to validate the new app on Android.

**Independent Test**: Launch the app, browse every group, and search for a hero by part of its name.

**Acceptance Scenarios**:

1. **Given** the app has just opened, **When** I view the hero selector, **Then** I can see the complete 45-hero Milestone 1 roster grouped into Dice Throne, Marvel, X-Men, and standalone or promo heroes.
2. **Given** I am viewing the hero selector, **When** I enter part of a hero name in search, **Then** I see only matching heroes from the complete roster, regardless of their group.
3. **Given** I have searched for a hero, **When** I clear the search text, **Then** grouped browsing returns.

### Edge Cases

- A search with no matching hero shows a clear empty-state message and does not hide the search field.
- Search ignores capitalization and leading or trailing spaces.
- The roster includes each canonical hero exactly once, including heroes with multi-word names such as Miles Morales Spider-Man and Vampire Lord.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The app MUST open to a hero-selection screen without requiring an account or internet connection.
- **FR-002**: The app MUST present all 45 canonical heroes in the frozen Milestone 1 roster exactly once.
- **FR-003**: The app MUST group browse results into Dice Throne, Marvel, X-Men, and Standalone or promo heroes.
- **FR-004**: Users MUST be able to search the entire roster by any part of a hero name.
- **FR-005**: Search MUST be case-insensitive and ignore leading and trailing spaces.
- **FR-006**: The app MUST show a clear message when no heroes match a search.
- **FR-007**: This feature MUST use original text-only presentation and no official logos, artwork, cards, boards, dice, or rules text.

### Key Entities

- **Hero**: One canonical selectable roster entry, with a display name and browse group.
- **Hero group**: One of the four browse categories used to organize the roster.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: A player can reach the full hero list within 5 seconds of opening the app on a supported Android device.
- **SC-002**: A player can find any one of the 45 supported heroes by entering part of its name.
- **SC-003**: The list displays 45 unique heroes when no search is active.
- **SC-004**: A search with no result communicates that outcome without preventing the player from changing or clearing the search.

## Assumptions

- This is the first app slice, so opening the app directly to hero selection is acceptable; the planned main navigation and match-logging flow are deferred.
- Selecting a hero, saving a match, and persisting data are out of scope for this feature.
- The four browse groups use the source documents' requested high-level grouping: Dice Throne combines Season 1 and Season 2; standalone or promo combines Santa vs Krampus, Standalone, Outcasts, and Vanguard.
