# Quickstart: Edit Back Navigation

1. Open a saved match from Match history and choose Edit match.
2. Change any value, then use Android's Back gesture.
3. Confirm `Discard changes?` appears. Choose Keep editing and confirm the changed draft remains.
4. Use Back again, choose Discard changes, and confirm Match history appears with the original saved values.
5. From a nested non-edit screen, use Back and confirm it returns one screen at a time; from a main destination, Android can exit.

## Results

- **2026-08-22 automated checks**: Passed. `testDebugUnitTest --no-daemon` and `assembleDebug --no-daemon` completed successfully using the installed Java 21 JDK.
- **2026-08-22 manual Android check**: Passed by the product owner. The Back confirmation, keep-editing path, discard path, return to history, and normal nested Back behavior worked as intended.
