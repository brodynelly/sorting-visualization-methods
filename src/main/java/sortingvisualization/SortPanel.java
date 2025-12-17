package sortingvisualization;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JPanel;

/**
 * The panel responsible for visualizing the sorting algorithms.
 *
 * @author Ouda
 * @author Brody Nelson
 */
public class SortPanel extends JPanel {

    // Array to hold the line lengths to be sorted
    private int[] lineLengths;
    // The number of lines to sort
    private static final int TOTAL_NUMBER_OF_LINES = 256;
    // Array to hold the scrambled lines copy
    private int[] scrambledLines;
    // Temporary array used for merge sorts
    private int[] tempArray;

    // Random generator
    private final Random random = new Random();

    public SortPanel() {
        lineLengths = new int[TOTAL_NUMBER_OF_LINES];
        for (int i = 0; i < TOTAL_NUMBER_OF_LINES; i++) {
            lineLengths[i] = i + 5;
        }
    }

    public int[] getLineLengths() {
        return lineLengths;
    }

    /**
     * Scrambles the lines randomly.
     */
    public void scrambleLines() {
        for (int i = 0; i < TOTAL_NUMBER_OF_LINES; i++) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }

        scrambledLines = new int[TOTAL_NUMBER_OF_LINES];
        System.arraycopy(lineLengths, 0, scrambledLines, 0, TOTAL_NUMBER_OF_LINES);

        repaint();
    }

    private void swap(int i, int j) {
        int temp = lineLengths[i];
        lineLengths[i] = lineLengths[j];
        lineLengths[j] = temp;
    }

    /**
     * Selection Sort Implementation
     */
    public void selectionSort() {
        Calendar start = Calendar.getInstance();

        for (int i = 0; i < TOTAL_NUMBER_OF_LINES - 1; i++) {
            int indexOfSmallest = getIndexOfSmallest(i, TOTAL_NUMBER_OF_LINES - 1);
            swap(i, indexOfSmallest);
            repaintAndSleep();
        }

        Calendar end = Calendar.getInstance();
        SortGui.selectionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    private int getIndexOfSmallest(int first, int last) {
        int min = lineLengths[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++) {
            if (lineLengths[index] < min) {
                min = lineLengths[index];
                indexOfMin = index;
            }
        }
        return indexOfMin;
    }

    /**
     * Recursive Merge Sort Implementation
     */
    public void recursiveMergeSort() {
        Calendar start = Calendar.getInstance();
        tempArray = new int[TOTAL_NUMBER_OF_LINES];
        recursiveMergeSort(0, TOTAL_NUMBER_OF_LINES - 1);

        Calendar end = Calendar.getInstance();
        SortGui.recursiveMergeTime = end.getTime().getTime() - start.getTime().getTime();
    }

    private void recursiveMergeSort(int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            recursiveMergeSort(first, mid);
            recursiveMergeSort(mid + 1, last);
            mergeRecursive(first, mid, last);
            repaintAndSleep();
        }
    }

    private void mergeRecursive(int first, int mid, int last) {
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        int index = beginHalf1;
        while ((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2)) {
            if (lineLengths[beginHalf1] < lineLengths[beginHalf2]) {
                tempArray[index] = lineLengths[beginHalf1];
                beginHalf1++;
            } else {
                tempArray[index] = lineLengths[beginHalf2];
                beginHalf2++;
            }
            index++;
        }

        while (beginHalf1 <= endHalf1) {
            tempArray[index] = lineLengths[beginHalf1];
            beginHalf1++;
            index++;
        }

        while (beginHalf2 <= endHalf2) {
            tempArray[index] = lineLengths[beginHalf2];
            beginHalf2++;
            index++;
        }

        for (index = first; index <= last; index++) {
            lineLengths[index] = tempArray[index];
        }
    }

    /**
     * Iterative Merge Sort Implementation
     */
    public void iterativeMergeSort() {
        Calendar start = Calendar.getInstance();
        tempArray = new int[TOTAL_NUMBER_OF_LINES];
        int beginLeftovers = TOTAL_NUMBER_OF_LINES;

        for (int segmentLength = 1; segmentLength <= TOTAL_NUMBER_OF_LINES / 2; segmentLength = 2 * segmentLength) {
            beginLeftovers = mergeSegmentPairs(TOTAL_NUMBER_OF_LINES, segmentLength);
            int endSegment = beginLeftovers + segmentLength - 1;
            if (endSegment < TOTAL_NUMBER_OF_LINES - 1) {
                mergeIterative(beginLeftovers, endSegment, TOTAL_NUMBER_OF_LINES - 1);
            }
        }

        if (beginLeftovers < TOTAL_NUMBER_OF_LINES) {
            mergeIterative(0, beginLeftovers - 1, TOTAL_NUMBER_OF_LINES - 1);
        }

        Calendar end = Calendar.getInstance();
        SortGui.iterativeMergeTime = end.getTime().getTime() - start.getTime().getTime();
    }

    private int mergeSegmentPairs(int l, int segmentLength) {
        int mergedPairLength = 2 * segmentLength;
        int numberOfPairs = l / mergedPairLength;

        int beginSegment1 = 0;
        for (int count = 1; count <= numberOfPairs; count++) {
            int endSegment1 = beginSegment1 + segmentLength - 1;
            int beginSegment2 = endSegment1 + 1;
            int endSegment2 = beginSegment2 + segmentLength - 1;
            mergeIterative(beginSegment1, endSegment1, endSegment2);

            beginSegment1 = endSegment2 + 1;
            repaintAndSleep();
        }
        return beginSegment1;
    }

    private void mergeIterative(int first, int mid, int last) {
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        int index = beginHalf1;
        while ((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2)) {
            if (lineLengths[beginHalf1] < lineLengths[beginHalf2]) {
                tempArray[index] = lineLengths[beginHalf1];
                beginHalf1++;
            } else {
                tempArray[index] = lineLengths[beginHalf2];
                beginHalf2++;
            }
            index++;
        }

        while (beginHalf1 <= endHalf1) {
            tempArray[index] = lineLengths[beginHalf1];
            beginHalf1++;
            index++;
        }

        while (beginHalf2 <= endHalf2) {
            tempArray[index] = lineLengths[beginHalf2];
            beginHalf2++;
            index++;
        }

        for (index = first; index <= last; index++) {
            lineLengths[index] = tempArray[index];
        }
    }

    /**
     * Bubble Sort Implementation
     */
    public void bubbleSort() {
        Calendar start = Calendar.getInstance();

        for (int pass = 1; pass < TOTAL_NUMBER_OF_LINES; pass++) {
            for (int index = 0; index < TOTAL_NUMBER_OF_LINES - pass; index++) {
                if (lineLengths[index] > lineLengths[index + 1]) {
                    swap(index, index + 1);
                    repaint(); // Update UI after swap
                }
            }
            delay(10); // Delay after each pass
        }

        Calendar end = Calendar.getInstance();
        SortGui.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
    }

    /**
     * Insertion Sort Implementation
     */
    public void insertionSort() {
        Calendar start = Calendar.getInstance();

        for (int unsorted = 1; unsorted < TOTAL_NUMBER_OF_LINES; unsorted++) {
            int firstUnsorted = lineLengths[unsorted];
            int index = unsorted;

            while ((index > 0) && (lineLengths[index - 1] > firstUnsorted)) {
                lineLengths[index] = lineLengths[index - 1];
                index--;
            }
            lineLengths[index] = firstUnsorted;
            repaintAndSleep();
        }

        Calendar end = Calendar.getInstance();
        SortGui.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    /**
     * Shell Sort Implementation
     */
    public void shellSort() {
        Calendar start = Calendar.getInstance();

        for (int gap = TOTAL_NUMBER_OF_LINES / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < TOTAL_NUMBER_OF_LINES; i++) {
                int temp = lineLengths[i];
                int j;
                for (j = i; j >= gap && lineLengths[j - gap] > temp; j = j - gap) {
                    lineLengths[j] = lineLengths[j - gap];
                }
                lineLengths[j] = temp;
            }
            repaintAndSleep();
        }

        Calendar end = Calendar.getInstance();
        SortGui.shellTime = end.getTime().getTime() - start.getTime().getTime();
    }

    /**
     * Quick Sort Implementation
     */
    public void quickSort() {
        Calendar start = Calendar.getInstance();
        quickSort(0, TOTAL_NUMBER_OF_LINES - 1);
        Calendar end = Calendar.getInstance();
        SortGui.quickTime = end.getTime().getTime() - start.getTime().getTime();
    }

    private void quickSort(int first, int last) {
        if (first < last) {
            int pivotIndex = partition(first, last);
            quickSort(first, pivotIndex - 1);
            quickSort(pivotIndex + 1, last);
        }
    }

    private int partition(int first, int last) {
        int pivot = lineLengths[last];
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (lineLengths[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, last);
        repaintAndSleep();
        return i + 1;
    }

    /**
     * Radix Sort Implementation
     */
    public void radixSort() {
        Calendar start = Calendar.getInstance();

        int max = getMax();

        for (int exp = 1; max / exp > 0; exp = exp * 10) {
            countSort(exp);
            repaintAndSleep();
        }

        Calendar end = Calendar.getInstance();
        SortGui.radixTime = end.getTime().getTime() - start.getTime().getTime();
    }

    private int getMax() {
        int max = lineLengths[0];
        for (int i = 1; i < TOTAL_NUMBER_OF_LINES; i++) {
            if (lineLengths[i] > max) {
                max = lineLengths[i];
            }
        }
        return max;
    }

    private void countSort(int exp) {
        int[] output = new int[TOTAL_NUMBER_OF_LINES];
        int[] count = new int[10];

        for (int i = 0; i < TOTAL_NUMBER_OF_LINES; i++) {
            count[(lineLengths[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }

        for (int i = TOTAL_NUMBER_OF_LINES - 1; i >= 0; i--) {
            output[count[(lineLengths[i] / exp) % 10] - 1] = lineLengths[i];
            count[(lineLengths[i] / exp) % 10]--;
        }

        for (int i = 0; i < TOTAL_NUMBER_OF_LINES; i++) {
            lineLengths[i] = output[i];
        }
    }

    /**
     * Resets the visualization to the scrambled state.
     */
    public void reset() {
        if (scrambledLines != null) {
            System.arraycopy(scrambledLines, 0, lineLengths, 0, TOTAL_NUMBER_OF_LINES);
            repaint();
        }
    }

    // Helper to repaint and wait
    private void repaintAndSleep() {
        paintImmediately(getBounds());
        delay(10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < TOTAL_NUMBER_OF_LINES; i++) {
            if (i % 8 == 0) {
                g.setColor(Color.green);
            } else if (i % 8 == 1) {
                g.setColor(Color.blue);
            } else if (i % 8 == 2) {
                g.setColor(Color.yellow);
            } else if (i % 8 == 3) {
                g.setColor(Color.red);
            } else if (i % 8 == 4) {
                g.setColor(Color.black);
            } else if (i % 8 == 5) {
                g.setColor(Color.orange);
            } else if (i % 8 == 6) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.gray);
            }

            g.drawLine(4 * i + 25, 300, 4 * i + 25, 300 - lineLengths[i]);
        }
    }

    private void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
