# Research: Select Match Outcome

## Decision: Represent the new choices as player roles

**Rationale**: Winner and first player both refer to one of the two participants. A shared two-value role keeps the temporary state clear, prevents unrelated free text, and makes the summary consistent even for mirror matches.

**Alternatives considered**:

- Store display text such as "You won": rejected because presentation wording should not be the stored choice.
- Add opponent identities: rejected because the initial core Milestone 1 scope explicitly has no opponent identities.

## Decision: Clear only dependent answers

**Rationale**: Choosing a different hero changes the context for result and turn order, so both are reset. Changing the winner leaves both heroes valid but requires turn order to be selected again, keeping the next choice sequence predictable.

**Alternatives considered**:

- Keep all later answers after an earlier change: rejected because it risks showing stale context.
- Clear the hero choices when result changes: rejected because result does not invalidate either hero.

## Decision: Finish at a temporary summary

**Rationale**: The required date, review, and save steps are deliberately later work. A summary proves that all four approved choices are retained without accidentally creating a match record.

**Alternatives considered**:

- Add a date field or save button: rejected because they exceed the approved slice.
