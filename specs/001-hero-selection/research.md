# Research: Hero Selection

## Decision: Start from the Android Studio Empty Activity template

**Rationale**: The official Android documentation identifies Android Studio as the supported development environment and its Empty Activity template as the direct starting point for a new Compose app. The template supplies a compatible build configuration and Gradle wrapper, which avoids hand-assembling build tooling for a first mobile project.

**Alternatives considered**:

- Hand-writing an Android/Gradle project: rejected because a new developer would have to solve build-tool compatibility before seeing the app.
- A cross-platform framework: rejected because the source documents approve Android-first Kotlin and Compose.

## Decision: Keep the catalog fixed in app code for this feature

**Rationale**: The project’s roster is frozen and the screen only needs to display and search it. There is no user-created information to save, so a database would add work without user value.

**Alternatives considered**:

- Room database: deferred to match logging, when product data actually needs persistence.
- Downloaded roster: rejected because Milestone 1 must be fully offline.

## Decision: Search the display name with trimmed, case-insensitive matching

**Rationale**: It is predictable for beginners, satisfies the product requirement that search covers the full roster, and handles ordinary typing differences without additional dependencies.

**Alternatives considered**:

- Fuzzy search: deferred; it is unnecessary for 45 names and would make matching rules less transparent.
- Group-only search: rejected because the product brief requires full-roster search.
