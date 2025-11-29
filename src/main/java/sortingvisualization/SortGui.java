package sortingvisualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * The main GUI class for the sorting visualization application.
 *
 * @author Ouda
 * @author Brody Nelson
 */
public class SortGui {

    // Variables to hold the execution time for each sort
    public static double selectionTime = 0.0;
    public static double recursiveMergeTime = 0.0;
    public static double iterativeMergeTime = 0.0;
    public static double bubbleTime = 0.0;
    public static double insertionTime = 0.0;
    public static double shellTime = 0.0;
    public static double quickTime = 0.0;
    public static double radixTime = 0.0;

    // Boolean variables to track completion status
    public boolean selectionDone = false;
    public boolean recursiveMergeDone = false;
    public boolean iterativeMergeDone = false;
    public boolean bubbleDone = false;
    public boolean insertionDone = false;
    public boolean shellDone = false;
    public boolean quickDone = false;
    public boolean radixDone = false;

    // The panel where sorting happens
    private final SortPanel sortPanel = new SortPanel();

    // Default constructor
    public SortGui() {
        MyScreen screen = new MyScreen();
        screen.setTitle("Sorting Visualization Assignment");
        screen.setSize(1400, 800);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
    }

    // Inner class extending JFrame for the main window
    public class MyScreen extends JFrame {

        // GUI Components
        JButton scrambleButton = new JButton("Scramble Lines");
        JRadioButton selectionButton = new JRadioButton("Selection");
        JRadioButton recursiveMergeButton = new JRadioButton("Merge Recursive");
        JRadioButton iterativeMergeButton = new JRadioButton("Merge Iterative");
        JRadioButton bubbleButton = new JRadioButton("Bubble");
        JRadioButton insertionButton = new JRadioButton("Insertion");
        JRadioButton shellButton = new JRadioButton("Shell");
        JRadioButton quickButton = new JRadioButton("Quick");
        JRadioButton radixButton = new JRadioButton("Radix");
        JRadioButton resetButton = new JRadioButton("Reset");

        JLabel selectionTimeLabel = new JLabel("Selection Time");
        JLabel selectionTimeTaken = new JLabel("");
        JLabel recursiveMergeTimeLabel = new JLabel("Merge-Rec Time");
        JLabel recursiveMergeTimeTaken = new JLabel("");
        JLabel iterativeMergeTimeLabel = new JLabel("Merge-Ita Time");
        JLabel iterativeMergeTimeTaken = new JLabel("");
        JLabel bubbleTimeLabel = new JLabel("Bubble Time");
        JLabel bubbleTimeTaken = new JLabel("");
        JLabel insertionTimeLabel = new JLabel("Insertion Time");
        JLabel insertionTimeTaken = new JLabel("");
        JLabel shellTimeLabel = new JLabel("Shell Time");
        JLabel shellTimeTaken = new JLabel("");
        JLabel quickTimeLabel = new JLabel("Quick Time");
        JLabel quickTimeTaken = new JLabel("");
        JLabel radixTimeLabel = new JLabel("Radix Time");
        JLabel radixTimeTaken = new JLabel("");

        public MyScreen() {
            // Set colors
            selectionTimeTaken.setForeground(Color.RED);
            recursiveMergeTimeTaken.setForeground(Color.RED);
            iterativeMergeTimeTaken.setForeground(Color.RED);
            bubbleTimeTaken.setForeground(Color.RED);
            insertionTimeTaken.setForeground(Color.RED);
            shellTimeTaken.setForeground(Color.RED);
            quickTimeTaken.setForeground(Color.RED);
            radixTimeTaken.setForeground(Color.RED);

            selectionButton.setForeground(Color.BLUE);
            recursiveMergeButton.setForeground(Color.BLUE);
            iterativeMergeButton.setForeground(Color.BLUE);
            bubbleButton.setForeground(Color.BLUE);
            insertionButton.setForeground(Color.BLUE);
            shellButton.setForeground(Color.BLUE);
            quickButton.setForeground(Color.BLUE);
            radixButton.setForeground(Color.BLUE);

            scrambleButton.setForeground(Color.BLUE);
            scrambleButton.setFont(new Font("Arial", Font.BOLD, 15));

            // Radio Button Panel
            JPanel algorithmPanel = new JPanel(new GridLayout(9, 1, 1, 1));
            algorithmPanel.add(selectionButton);
            algorithmPanel.add(recursiveMergeButton);
            algorithmPanel.add(iterativeMergeButton);
            algorithmPanel.add(bubbleButton);
            algorithmPanel.add(insertionButton);
            algorithmPanel.add(shellButton);
            algorithmPanel.add(quickButton);
            algorithmPanel.add(radixButton);
            algorithmPanel.add(resetButton);
            algorithmPanel.setBorder(new javax.swing.border.TitledBorder("Sort Algorithms"));

            // Time Panel
            JPanel timePanel = new JPanel(new GridLayout(16, 1, 1, 1));
            timePanel.add(selectionTimeLabel);
            timePanel.add(selectionTimeTaken);
            timePanel.add(recursiveMergeTimeLabel);
            timePanel.add(recursiveMergeTimeTaken);
            timePanel.add(iterativeMergeTimeLabel);
            timePanel.add(iterativeMergeTimeTaken);
            timePanel.add(bubbleTimeLabel);
            timePanel.add(bubbleTimeTaken);
            timePanel.add(insertionTimeLabel);
            timePanel.add(insertionTimeTaken);
            timePanel.add(shellTimeLabel);
            timePanel.add(shellTimeTaken);
            timePanel.add(quickTimeLabel);
            timePanel.add(quickTimeTaken);
            timePanel.add(radixTimeLabel);
            timePanel.add(radixTimeTaken);

            // Control Panel
            JPanel controlPanel = new JPanel(new GridLayout(3, 1, 2, 2));
            controlPanel.add(scrambleButton);
            controlPanel.add(algorithmPanel);
            controlPanel.add(timePanel);

            add(controlPanel, BorderLayout.EAST);
            add(sortPanel, BorderLayout.CENTER);

            // Initial State
            setAvailableChoices(false, false, false, false, false, false, false, false, false);

            // Action Listeners
            scrambleButton.addActionListener(e -> {
                sortPanel.scrambleLines();
                scrambleButton.setEnabled(false);
                setAvailableChoices(true, true, true, true, true, true, true, true, false);
            });

            selectionButton.addActionListener(e -> {
                sortPanel.selectionSort();
                selectionDone = true;
                selectionTimeTaken.setText(selectionTime / 1000 + " Seconds");
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            recursiveMergeButton.addActionListener(e -> {
                sortPanel.recursiveMergeSort();
                recursiveMergeTimeTaken.setText((recursiveMergeTime / 1000) + " Seconds");
                recursiveMergeDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            iterativeMergeButton.addActionListener(e -> {
                sortPanel.iterativeMergeSort();
                iterativeMergeTimeTaken.setText((iterativeMergeTime / 1000) + " Seconds");
                iterativeMergeDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            bubbleButton.addActionListener(e -> {
                sortPanel.bubbleSort();
                bubbleTimeTaken.setText((bubbleTime / 1000) + " Seconds");
                bubbleDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            insertionButton.addActionListener(e -> {
                sortPanel.insertionSort();
                insertionTimeTaken.setText((insertionTime / 1000) + " Seconds");
                insertionDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            shellButton.addActionListener(e -> {
                sortPanel.shellSort();
                shellTimeTaken.setText((shellTime / 1000) + " Seconds");
                shellDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            quickButton.addActionListener(e -> {
                sortPanel.quickSort();
                quickTimeTaken.setText((quickTime / 1000) + " Seconds");
                quickDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            radixButton.addActionListener(e -> {
                sortPanel.radixSort();
                radixTimeTaken.setText((radixTime / 1000) + " Seconds");
                radixDone = true;
                setAvailableChoices(false, false, false, false, false, false, false, false, true);
            });

            resetButton.addActionListener(e -> {
                resetButton.setEnabled(false);
                sortPanel.reset();

                if (selectionDone && recursiveMergeDone && iterativeMergeDone && bubbleDone && insertionDone && shellDone && quickDone && radixDone) {
                    scrambleButton.setEnabled(true);
                    resetAllDoneFlags();
                    setAvailableChoices(false, false, false, false, false, false, false, false, false);
                    clearTimeLabels();
                } else {
                    setAvailableChoices(!selectionDone, !recursiveMergeDone, !iterativeMergeDone, !bubbleDone, !insertionDone, !shellDone, !quickDone, !radixDone, false);
                }
            });
        }

        public void setAvailableChoices(boolean selectionState, boolean rmergeState, boolean imergeState,
                                        boolean bubbleState, boolean insertionState, boolean shellState, boolean quickState, boolean radixState, boolean resetState) {
            this.selectionButton.setEnabled(selectionState);
            this.recursiveMergeButton.setEnabled(rmergeState);
            this.iterativeMergeButton.setEnabled(imergeState);
            this.bubbleButton.setEnabled(bubbleState);
            this.insertionButton.setEnabled(insertionState);
            this.shellButton.setEnabled(shellState);
            this.quickButton.setEnabled(quickState);
            this.radixButton.setEnabled(radixState);
            this.resetButton.setEnabled(resetState);
        }

        private void resetAllDoneFlags() {
            selectionDone = false;
            recursiveMergeDone = false;
            iterativeMergeDone = false;
            bubbleDone = false;
            insertionDone = false;
            shellDone = false;
            quickDone = false;
            radixDone = false;
        }

        private void clearTimeLabels() {
            selectionTimeTaken.setText("");
            recursiveMergeTimeTaken.setText("");
            iterativeMergeTimeTaken.setText("");
            bubbleTimeTaken.setText("");
            insertionTimeTaken.setText("");
            shellTimeTaken.setText("");
            quickTimeTaken.setText("");
            radixTimeTaken.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortGui::new);
    }
}
