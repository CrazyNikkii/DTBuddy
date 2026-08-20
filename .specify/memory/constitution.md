# DTBuddy Working Rules

## What decides the project

`PROJECT.md` and `TECHNICAL_CONSTRAINTS.md` are DTBuddy's source of truth.
They take priority over this document, every feature specification, every
technical plan, and every task list. Do not edit either source document unless
the product owner explicitly asks for that edit.

When documents disagree or a decision is missing, stop and explain the issue
plainly to the product owner. Do not guess or silently change the scope.

## Build small, approved pieces

Work on one small, useful piece at a time. Before changing the project, inspect
the source documents and current work, recommend the single best next piece,
explain it in plain English, and wait for approval.

After approval, record the agreed feature specification, plan, and task list in
the relevant `specs/###-short-name/` folder, then implement only that approved
work. Do not add unrelated features, polish, libraries, or future-milestone
work.

## Respect the milestones and constraints

Milestone 1 is an Android-only, fully local and offline app. It uses Kotlin,
Jetpack Compose, and Room/SQLite as defined in `TECHNICAL_CONSTRAINTS.md`.
Do not add accounts, hosted services, a backend, Google sign-in, or community
features before their approved milestone.

Keep solutions simple and proportionate to the current milestone. Add a new
architecture layer, dependency, or service only when the approved work has a
clear need for it.

## Protect users and project data

Follow the privacy, security, intellectual-property, hosting, and credential
rules in the source documents. Never put credentials, recovery keys, player
data, Google identity data, session tokens, or private notes in source control
or technical logs.

## Check and review every change

Run suitable checks for the work performed and report the result in plain
English. A separate fresh Codex task reviews completed changes without editing
them. Must-fix findings are corrected before the work is approved; optional
improvements are recorded only when useful and may be postponed.

## Keep the record useful

Feature documents explain one agreed piece of work; they do not replace the
source documents or become a second product brief. Keep them concise, current,
and understandable to a non-programmer. Record decisions that affect future
work, including anything deliberately postponed.

## Governance

These rules support the two-prompt workflow: one implementation task recommends
and then, after approval, performs the next small piece of work; one separate
review task independently checks it. Changes to these working rules require the
product owner's approval and must remain consistent with the two source
documents.

**Version**: 1.0.0 | **Ratified**: 2026-08-20 | **Last Amended**: 2026-08-20
