# DTBuddy — Technical Constraints and Hosting Decisions

## Purpose and status

This document records approved technical constraints and infrastructure
decisions. It is intentionally not the software design document (SDD); the SDD
will define the architecture and implementation details within these limits.

## Budget

- Ongoing hosting and software cost must be **$0**.
- The Google Play developer registration fee is accepted as the existing
  one-time exception.
- A DTBuddy domain is an accepted recurring cost. The currently quoted
  `dtbuddy.com` registration price is approximately **€12.90 per year**;
  confirm tax and renewal pricing at purchase.

## Delivery and hosting model

- **Milestone 1:** fully local/offline on the Android device. It has no backend,
  hosted database, Google sign-in, or account requirement.
- **Milestone 2:** self-host the backend on the existing laptop for the
  friends-only private beta.
- **Before public launch:** reassess whether the laptop is sufficiently
  reliable, secure, and performant. If it is not, migrate to a hosted provider.

## Existing host environment

- Host: Packard Bell EasyNote TE11HC.
- Operating system: headless Debian 13, administered through SSH.
- CPU: Intel Pentium B960.
- Memory: 4 GB DDR3 RAM.
- Storage: 500 GB HDD.
- Current workload: one Node.js Discord bot, managed by `systemd`, using a
  local loopback-only PostgreSQL database.
- Docker is not required initially; prefer a small Node.js service managed by
  `systemd` to conserve the limited memory.

## Milestone 2 self-hosting requirements

- DTBuddy uses a separate PostgreSQL database and database credentials. It
  must not share the Discord bot's database tables or credentials.
- The Android app communicates only with the DTBuddy API; it never connects
  directly to PostgreSQL.
- The API and database remain on the laptop.
- A Cloudflare Tunnel runs on the laptop as a `systemd` service and publishes
  only the local DTBuddy API. It uses an outbound connection, so no router
  port forwarding or static public IP is required.
- The PostgreSQL port is not publicly exposed.
- Buy and use a DTBuddy domain when Milestone 2 infrastructure is introduced;
  manage its DNS through Cloudflare for a stable API hostname.

## Availability and data protection

- During the Milestone 2 friends-only private beta, occasional laptop, power,
  internet, or database outages and possible data loss are acceptable.
- Before public launch, add automated PostgreSQL backups to an external HDD or
  other separate storage, and test restoring a backup.
- Public-launch reliability and data-loss targets are deferred for later
  decision.

## Operations and maintenance

- The laptop automatically installs routine Debian security fixes when they do
  not require a restart. It never restarts itself automatically; reboots stay
  under the product owner's control so the app is not unexpectedly taken
  offline.
- The DTBuddy API and Cloudflare Tunnel run as managed system services. If
  either stops unexpectedly, the laptop automatically attempts to start it
  again. This does not reboot the laptop and adds virtually no ongoing resource
  use.
- A simple monthly maintenance checklist will check and apply necessary
  Debian, Node.js, PostgreSQL, and Cloudflare Tunnel updates.
- The API keeps a small, automatically overwritten technical error log for 30
  days to help diagnose problems. It records only the time, affected app
  function, and technical error information; it never records account details,
  Google identity details, session tokens, private notes, or match contents.
- Before public launch, consider alerting the product owner through the
  existing Discord bot when the host has pending updates or a failed service.

## Approved Android application technology

- The Android app is written in Kotlin.
- Its user interface uses Jetpack Compose, Android's modern native UI toolkit.
- The minimum supported Android version is Android 8.0 (API level 26).
- At release time, the app targets the Android API level then required by
  Google Play; this is independent of the minimum supported version.
- The project is Android-first. A future iOS app remains possible; this choice
  does not commit the project to iOS work or prevent a later Kotlin
  Multiplatform or native-iOS approach.

## Approved Milestone 1 local data storage

- Milestone 1 stores product data in a Room database backed by SQLite in the
  Android app's private on-device storage.
- This database stores the local owner profile, matches, private notes, and
  favourite-hero selection and order. It is the source for Milestone 1
  history and statistics while offline.
- Data in private app storage is local to the device and can be lost if the
  app is uninstalled, the device is lost, or its app data is cleared.

## Approved Android application structure

- Compose screens display the user interface and send user actions only.
- Navigation Compose manages movement between screens and Android back
  navigation.
- ViewModels hold screen state and coordinate actions such as saving, editing,
  and deleting matches.
- Repositories are the data boundary. They access Room in Milestone 1 and can
  later coordinate local data with the DTBuddy API in Milestone 2.
- Milestone 1 uses a small manual application container to create and share
  common app services such as repositories and the Room database. Dependency
  injection frameworks such as Hilt are deferred unless the setup becomes
  repetitive enough to justify them.
- The project does not add further architectural layers unless a concrete
  future need justifies them.

## Milestone 1-to-2 test-data transition

- Milestone 1 data is disposable development and solo-test data. It is not
  imported into Milestone 2 accounts.
- Milestone 2 Google accounts begin with empty personal data. Real match data
  starts during the friends-only private beta.

## Approved Milestone 2 offline and sync behaviour

- Milestone 2 retains local app data on each device and saves new changes
  locally before attempting to sync them to the DTBuddy API.
- Changes made while the device or laptop host is unavailable are queued and
  retried when connectivity returns. The app clearly indicates pending, synced,
  or failed sync state.
- If different devices change the same match before syncing, the version saved
  most recently becomes the active version automatically. The previous version
  is retained as a record for 90 days, then removed automatically. The app
  does not interrupt the player to choose between versions; this keeps the
  private beta simple and has only a small storage and processing cost on the
  laptop host.
- Shared or live features, including player search, linked-match requests, and
  current community statistics, require a connection to the API and may be
  unavailable or stale while offline.

## Approved Milestone 2 API style

- The backend uses Node.js, Express 5, and TypeScript.
- The Android app communicates with the DTBuddy Node.js backend over HTTPS
  using JSON request and response bodies.
- The API uses REST-style endpoints for resources and actions such as matches,
  player profiles, statistics, and linked-match requests.
- GraphQL and persistent real-time socket connections are not part of the
  initial API design.
- The API validates all external request input with Zod schemas. Business-rule
  and permission checks remain server-side even after schema validation.
- The API includes a lightweight built-in speed limit for online requests.
  Normal use is unaffected, but excessive repeated requests from the same
  account or network are temporarily refused. This protects the limited laptop
  host without introducing another hosted service or ongoing cost.
- During the friends-only beta, the API calculates statistics from the actual
  match records when a player opens a statistics view. It does not maintain a
  separate, constantly updated copy of those figures. This is accurate,
  lightweight, and sufficient for the small beta group; reassess it before
  public launch if usage grows.

## Approved Google sign-in implementation

- The Android app uses Android Credential Manager for the approved
  Google-only sign-in flow.
- The app sends a Google ID token to the DTBuddy API over HTTPS. The API
  verifies the token before creating or finding the DTBuddy account.
- DTBuddy does not collect, handle, or store Google passwords. It requests
  only the basic identity information needed for account creation.
- The account link uses Google's verified stable subject identifier rather
  than the user's email address.
- This direct implementation uses no paid Google authentication service and
  does not require the product owner to operate as a registered company.

## Approved account-session implementation

- After Google identity verification, the DTBuddy API creates a server-managed
  account session in PostgreSQL and returns a random session token to the app.
- The Android app stores the token using Android secure credential storage and
  sends it with authenticated API requests.
- The API can revoke a session immediately when the player signs out or their
  account is deleted. Session records are small, indexed database records and
  are appropriate for the limited laptop host during the friends beta.
- Sessions do not expire merely because the player has not used the app for a
  period of time. They stay active until the player signs out, deletes their
  account, or DTBuddy revokes the session for a clear security reason. The app
  stores board-game results rather than sensitive private content, so avoiding
  routine sign-in prompts takes priority over an inactivity timeout.

## Approved PostgreSQL schema changes

- The Node.js API accesses PostgreSQL through the lightweight `pg`
  (node-postgres) library.
- It uses a deliberately small, capped connection pool, initially limited to
  five connections, to keep resource use predictable on the beta laptop host.
- Every PostgreSQL schema change is a versioned SQL migration file committed to
  Git.
- Migrations are reviewed and applied explicitly as part of deployment, after
  an appropriate database backup. The API does not automatically alter the
  database schema on startup.
- A small project-owned Node.js migration runner applies the versioned SQL
  files and records which versions have been applied in PostgreSQL. It runs
  only during a deliberate deployment; it is not a continuously running
  service. This avoids extra memory and background-process overhead on the
  limited Milestone 2 laptop host while keeping database updates traceable and
  repeatable.

## Deferred technical decisions

- Exact backup schedule, retention, encryption, and restore objective.
- Public-launch hosting decision and migration plan.
- Monitoring, alerting, privacy-policy hosting, and incident-response details.
