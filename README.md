# Sorting Visualization

A Java Swing application that visualizes various sorting algorithms.

## Features

- **Visualizes 8 sorting algorithms:**
  - Selection Sort
  - Recursive Merge Sort
  - Iterative Merge Sort
  - Bubble Sort
  - Insertion Sort
  - Shell Sort
  - Quick Sort
  - Radix Sort
- **Comparison:** Shows execution time for each algorithm.
- **Interactive:**
  - "Scramble Lines" to randomize input.
  - Radio buttons to select algorithms.
  - "Reset" to clear visualization.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/sorting-visualization-java.git
    cd sorting-visualization-java
    ```

2.  **Build the project:**

    ```bash
    mvn clean package
    ```

## Usage

1.  **Run the application:**

    ```bash
    java -cp target/sorting-visualization-1.0-SNAPSHOT.jar sortingvisualization.SortGui
    ```
    *(Alternatively, you can run `SortGui.main()` from your IDE)*

2.  **Interact:**
    - Click **Scramble Lines** to start.
    - Select a sorting algorithm to watch it sort the lines.
    - The execution time will be displayed next to the algorithm name.
    - Click **Reset** to enable scrambling again after trying algorithms.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── sortingvisualization/
│           ├── SortGui.java      # Main entry point and GUI setup
│           └── SortPanel.java    # Logic and rendering of sorting algorithms
└── test/
    └── java/
        └── sortingvisualization/
            └── SortingLogicTest.java # JUnit tests for sorting logic
```

## Development

- **Run tests:**
  ```bash
  mvn test
  ```

- **Check code style:**
  ```bash
  mvn checkstyle:check
  ```

## License

This project is open source.
