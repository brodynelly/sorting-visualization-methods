# AGENTS.md

## Repository Instructions

This repository is a Java Swing application for visualizing sorting algorithms.

### Standards

- **Language:** Java 17+
- **Build System:** Maven
- **Style:** Checkstyle (Sun Checks with modifications).
- **Testing:** JUnit 5.

### Workflow

1.  **Code Changes:**
    - Use `src/main/java/sortingvisualization` for source code.
    - Use `src/test/java/sortingvisualization` for tests.
2.  **Verification:**
    - Always run `mvn test` before committing.
    - Ensure `mvn checkstyle:check` passes.
3.  **Naming:**
    - Classes: `PascalCase`
    - Methods/Variables: `camelCase`
    - Constants: `UPPER_SNAKE_CASE`

### CI/CD

- GitHub Actions is configured to run tests and checkstyle on push.
