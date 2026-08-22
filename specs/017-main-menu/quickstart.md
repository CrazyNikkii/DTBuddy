# Quickstart: Main Menu

1. Launch the app and confirm the main menu appears.
2. Choose Log a match and confirm the player-hero question opens.
3. Use Back on the first question: choose Keep logging, then use Back again and choose Discard log. Confirm the main menu returns and no match was saved.
4. Start again, reach Who won?, and use Back. Confirm the opponent-hero question appears without a discard dialog.
5. Save a completed match, then use Back from Match saved. Confirm the main menu appears and no logging question reopens.
6. Open Requests, Profile, and Global stats in turn. From each root screen, use Back and confirm the main menu appears; then use Back from the main menu and confirm Android can close the app.
7. From the main menu, use Back once and confirm Press Back again to exit appears. Use Back again within two seconds and confirm the app closes; relaunch, wait more than two seconds after the first Back, and confirm the next Back only shows the message again.

## Results

- **2026-08-22 automated checks**: Passed. `testDebugUnitTest --no-daemon` and `assembleDebug --no-daemon` completed successfully using the installed Java 21 JDK.
- **2026-08-22 manual Android check**: Passed by the product owner. The main-menu launch, guided-flow Back behavior, saved-match return, primary-destination return, and two-gesture exit behavior all worked as intended.
