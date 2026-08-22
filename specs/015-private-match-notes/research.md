# Research: Private Match Notes

## Decision: Use one nullable note field with a 500-character UI limit

**Rationale**: A nullable field represents the product distinction between no note and a saved note without a separate table or model. The UI limit follows the approved feature assumption and keeps history readable.

**Alternatives considered**: A separate notes table adds needless relationships for one note per match; unlimited notes make a small mobile increment harder to review.

## Decision: Trim input before storage

**Rationale**: Whitespace-only input becomes no note, as required. Meaningful leading or trailing whitespace is not useful in a short personal note.

**Alternatives considered**: Saving whitespace-only text would produce an empty-looking note row.

## Decision: Migrate existing local data explicitly

**Rationale**: Existing solo-test matches must remain available. The migration adds a nullable column, so prior rows naturally become no-note matches.

**Alternatives considered**: Recreating the database would discard local history and violate the feature requirement.

## Decision: Enter notes on the existing review and edit overview, display them in history

**Rationale**: This keeps normal logging guided and lets players revisit or correct notes without adding a new detail screen.

**Alternatives considered**: A separate match-detail screen is a broader capability than this approved increment.
