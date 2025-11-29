package sortingvisualization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SortingLogicTest {

    private SortPanel sortPanel;

    @BeforeEach
    public void setUp() {
        sortPanel = new SortPanel();
        // Scramble lines before each test to ensure we are sorting a shuffled array
        sortPanel.scrambleLines();
    }

    private void assertSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] <= array[i + 1], "Array is not sorted at index " + i + ": " + array[i] + " > " + array[i + 1]);
        }
    }

    @Test
    public void testSelectionSort() {
        sortPanel.selectionSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testRecursiveMergeSort() {
        sortPanel.recursiveMergeSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testIterativeMergeSort() {
        sortPanel.iterativeMergeSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testBubbleSort() {
        sortPanel.bubbleSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testInsertionSort() {
        sortPanel.insertionSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testShellSort() {
        sortPanel.shellSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testQuickSort() {
        sortPanel.quickSort();
        assertSorted(sortPanel.getLineLengths());
    }

    @Test
    public void testRadixSort() {
        sortPanel.radixSort();
        assertSorted(sortPanel.getLineLengths());
    }
}
