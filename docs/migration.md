# Migration Guide

This document details the changes made during the refactoring process to improve code quality, maintainability, and tooling.

## Summary of Changes

### 1. Repository Structure & Naming
- **Package Rename:** `bsn3g9_cs4050_assignment1` -> `sortingvisualization`.
- **File Renames:**
  - `SortGUI.java` -> `SortGui.java` (PascalCase).
  - `SortShow.java` -> `SortPanel.java` (More descriptive).
- **Directory Structure:** Adhered to standard Maven layout (`src/main/java`, `src/test/java`).

### 2. Code Improvements
- **Conventions:**
  - Standardized variable names to `camelCase` (e.g., `lines_lengths` -> `lineLengths`, `Selection_Done` -> `selectionDone`).
  - Standardized constant names (e.g., `total_number_of_lines` -> `TOTAL_NUMBER_OF_LINES`).
  - Removed unused imports and variables.
  - Added visibility modifiers (private fields).
- **Readability:**
  - improved comments.
  - Renamed methods to follow Java conventions (e.g., `scramble_the_lines` -> `scrambleLines`).

### 3. Tooling & Dependencies
- **Build System:** Migrated to **Maven** (`pom.xml` added).
- **Testing:** Added **JUnit 5** dependencies and implemented unit tests in `SortingLogicTest.java`.
- **Linting:** Added **Checkstyle** configuration (`checkstyle.xml`) and Maven plugin to enforce coding standards.
- **CI/CD:** Added GitHub Actions workflow (`.github/workflows/ci.yml`) to automatically build and test on push/PR.
- **Configuration:** Added `.editorconfig` for consistent editor settings.

## How to Verify
1. Run `mvn clean package` to ensure the project builds successfully.
2. Run `mvn test` to execute the new test suite.
3. Run `mvn checkstyle:check` to verify code style compliance.
