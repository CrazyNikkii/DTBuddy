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

- Routine operating-system security updates should be automated where safe.
- A simple monthly maintenance checklist will check and apply necessary
  Debian, Node.js, PostgreSQL, and Cloudflare Tunnel updates.
- Reboots remain under the product owner's control.
- Before public launch, consider alerting the product owner through the
  existing Discord bot when the host has pending updates or a failed service.

## Approved Android application technology

- The Android app is written in Kotlin.
- Its user interface uses Jetpack Compose, Android's modern native UI toolkit.
- The project is Android-first. A future iOS app remains possible; this choice
  does not commit the project to iOS work or prevent a later Kotlin
  Multiplatform or native-iOS approach.

## Deferred technical decisions

- Backend framework and API design.
- Database schema and migration tooling.
- Google sign-in implementation and credential management.
- Exact backup schedule, retention, encryption, and restore objective.
- Public-launch hosting decision and migration plan.
- Monitoring, alerting, privacy-policy hosting, and incident-response details.
