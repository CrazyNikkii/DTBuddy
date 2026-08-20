# Research: Select Match Heroes

## Decision: Use one reusable picker with ViewModel flow state

**Rationale**: Both steps have identical browse and search needs. Passing a role and selection callback avoids duplicated roster UI and makes the boundary between the two temporary choices clear. Navigation Compose follows the approved Android structure for movement and system Back behavior; a small ViewModel retains choices across ordinary activity recreation without saving a match.

**Alternatives considered**:

- Two copied screens: rejected because duplicated filtering and grouping could drift.
- Switch destinations only in Compose state: rejected because the approved Android structure requires Navigation Compose to manage movement and Android Back behavior.
- Persist a draft: rejected because unfinished-match storage is outside the approved slice.

## Decision: Allow mirror matches

**Rationale**: The confirmed product requirements do not forbid the same canonical hero for each player.

**Alternatives considered**:

- Exclude the chosen logging-player hero from the opponent list: rejected because it adds an unapproved game rule.
