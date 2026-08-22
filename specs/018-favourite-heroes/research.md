# Research: Favourite Heroes

## Decision: Store favourites as ordered Room rows

**Rationale**: The technical constraints require Room/SQLite to store favourite-hero selection and order. A table with a hero name and position preserves the small ordered list across app restarts and is easy to migrate without touching match rows.

**Alternatives considered**: In-memory state was rejected because it disappears after restart. Preferences storage was rejected because the source of truth assigns favourites to the Room database.

## Decision: Limit and validate in the repository

**Rationale**: The repository is the existing data boundary. It can enforce the maximum of three distinct canonical roster names regardless of which screen requests a change.

**Alternatives considered**: UI-only validation was rejected because another screen could bypass the rule. A new service layer was rejected as disproportionate.

## Decision: Use move-up and move-down controls

**Rationale**: These clear controls preserve a known order, work with accessibility services, and avoid introducing drag-and-drop complexity for three records.

## Decision: Load favourites through the ViewModel

**Rationale**: This preserves the approved separation: Compose renders state and sends actions; the ViewModel coordinates repository access.
