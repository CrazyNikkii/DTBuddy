# Implementation Plan: Main Menu

Use the existing Log match destination as the new root screen. It conditionally displays a Compose main menu or the existing navigation flow. The first hero-selection route handles Back with the existing discard-confirmation pattern; later routes retain their ordinary back-stack behaviour. No data or dependency change is needed.
